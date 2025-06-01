package org.example.ucr.loi.cs205.proj2.search

import org.example.ucr.loi.cs205.proj2.instance.Instance

class BackwardElimination : FeatureSearch {

    /**
     * This function performs backward feature elimination using leave-one-out cross-validation
     * and 1-nearest neighbor classification to find the most effective subset of features.
     *
     * At each step, this method evaluates the accuracy of removing each remaining feature
     * to the current subset, and greedily selects the one that least affects performance.
     *
     * @param instances a list of input instances
     * @return the best subset of features
     */
    override fun selectFeatures(instances: List<Instance>): List<Int> {
        val totalFeatures = instances[0].features.size
        val selected = instances[0].features.indices.toMutableList()

        println("This dataset has $totalFeatures features (not including the class attribute), with ${instances.size} instances.")

        val fullAccuracy = crossValidationAccuracy(instances, selected)
        println("Running nearest neighbor with all $totalFeatures features, using \"leaving-one-out\" evaluation, I get an accuracy of ${fullAccuracy}%")
        println("Beginning search.")

        var bestSet = selected.toList()
        var bestSoFarAccuracy = fullAccuracy

        while (selected.size > 1) {
            var levelBestFeatureToRemove: Int? = null
            var levelBestAccuracy = -1.0

            for (feature in selected) {
                val trial = selected.filter { it != feature }
                val acc = crossValidationAccuracy(instances, trial)
                println("   Using feature(s) ${trial.toOneBasedString()} accuracy is ${acc}%")

                if (acc > levelBestAccuracy) {
                    levelBestAccuracy = acc
                    levelBestFeatureToRemove = feature
                }
            }

            if (levelBestFeatureToRemove != null) {
                selected.remove(levelBestFeatureToRemove)
                println("Feature set ${selected.toOneBasedString()} was best, accuracy is ${levelBestAccuracy}%")

                if (levelBestAccuracy > bestSoFarAccuracy) {
                    bestSoFarAccuracy = levelBestAccuracy
                    bestSet = selected.toList()
                }
            } else {
                break
            }
        }

        println("Finished search!! The best feature subset is ${bestSet.toOneBasedString()}, which has an accuracy of ${bestSoFarAccuracy}%")
        return bestSet
    }

    private fun List<Int>.toOneBasedString(): String =
        joinToString(prefix = "{", postfix = "}") { (it + 1).toString() }
}
