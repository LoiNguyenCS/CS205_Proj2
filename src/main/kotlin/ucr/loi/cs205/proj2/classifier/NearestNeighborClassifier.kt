package org.example.ucr.loi.cs205.proj2.classifier

import org.example.ucr.loi.cs205.proj2.instance.Instance

class NearestNeighborClassifier(
    private val trainingData: List<Instance>
) {

    fun predict(query: Instance, featureIndices: List<Int>): Int {
        return trainingData.minByOrNull { instance ->
            euclideanDistance(query.features, instance.features, featureIndices)
        }?.classBelonged ?: error("Training data is empty")
    }

    private fun euclideanDistance(a: DoubleArray, b: DoubleArray, indices: List<Int>): Double {
        return indices.sumOf { i -> (a[i] - b[i]).let { it * it } }
    }
}
