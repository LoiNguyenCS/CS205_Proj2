package org.example.ucr.loi.cs205.proj2.search

import org.example.ucr.loi.cs205.proj2.instance.Instance

class ForwardSearch : FeatureSearch {

    /**
     * This function performs forward feature selection using leave-one-out cross-validation
     * and 1-nearest neighbor classification to find the most effective subset of features.
     *
     * At each step, this method evaluates the accuracy of adding each remaining feature
     * to the current subset, and greedily selects the one that most improves performance.
     * The process continues until all features are used.
     *
     * @param instances a list of input instances
     * @return the best subset of features
     */
    override fun selectFeatures(instances: List<Instance>): List<Int> {
        val totalFeatures = instances[0].features.size
        val allFeatures = (0 until totalFeatures).toList()
        val selected = mutableListOf<Int>()
        val available = allFeatures.toMutableSet()

        println("This dataset has ${totalFeatures} features (not including the class attribute), with ${instances.size} instances.")
        val fullAccuracy = crossValidationAccuracy(instances, allFeatures)
        println("Running nearest neighbor with all $totalFeatures features, using “leaving-one-out” evaluation, I get an accuracy of $fullAccuracy%\n")

        println("Beginning Search...")

        var bestSet = emptyList<Int>()
        var bestSoFarAccuracy = 0.0

        while (available.isNotEmpty()) {
            var levelBestFeature: Int? = null
            var levelBestAccuracy = -1.0

            for (feature in available) {
                val trial = selected + feature
                val acc = crossValidationAccuracy(instances, trial)
                println("   Using feature(s) $trial accuracy is $acc%")
                if (acc > levelBestAccuracy) {
                    levelBestAccuracy = acc
                    levelBestFeature = feature
                }
            }

            if (levelBestFeature != null) {
                selected += levelBestFeature
                available -= levelBestFeature
                println("Feature set $selected was best, accuracy is $levelBestAccuracy%")

                if (levelBestAccuracy > bestSoFarAccuracy) {
                    bestSoFarAccuracy = levelBestAccuracy
                    bestSet = selected.toList()
                }
            } else break
        }

        println("Finished search!! The best feature subset is $bestSet, which has an accuracy of $bestSoFarAccuracy%")
        return bestSet
    }
}
