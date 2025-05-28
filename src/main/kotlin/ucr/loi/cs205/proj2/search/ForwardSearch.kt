package org.example.ucr.loi.cs205.proj2.search

import org.example.ucr.loi.cs205.proj2.instance.Instance

class ForwardSearch : FeatureSearch {

    override fun selectFeatures(instances: List<Instance>): List<Int> {
        val totalFeatures = instances[0].features.size
        val allFeatures = (0 until totalFeatures).toList()
        val selected = mutableListOf<Int>()
        val available = allFeatures.toMutableSet()

        println("Dataset Summary:")
        println("Number of instances: ${instances.size}")
        println("Number of available features (excluding class label): $totalFeatures")

        val fullAccuracy = crossValidationAccuracy(instances, allFeatures)
        println("Baseline Accuracy:")
        println("Using all features with leave-one-out evaluation: $fullAccuracy accuracy%\n")

        println("Starting Forward Feature Selection...")

        var bestSet = emptyList<Int>()
        var bestSoFarAccuracy = 0.0

        while (available.isNotEmpty()) {
            var levelBestFeature: Int? = null
            var levelBestAccuracy = -1.0

            for (feature in available) {
                val trial = selected + feature
                val acc = crossValidationAccuracy(instances, trial)
                println("Using feature(s) $trial accuracy is $acc%")
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
