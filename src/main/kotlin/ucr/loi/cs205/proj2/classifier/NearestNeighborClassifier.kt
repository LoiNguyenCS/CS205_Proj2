package org.example.ucr.loi.cs205.proj2.classifier

import org.example.ucr.loi.cs205.proj2.instance.Instance

/**
 * This class performs the nearest neighbor classifier
 */
class NearestNeighborClassifier(
    /**
     * Input training data. Any new instance will be classified based on this training data.
     */
    private val trainingData: List<Instance>
) {

    /**
     * This function returns the nearest neighbor of an Instance, relative to a subset of features.
     *
     * @param query the Instance to be considered
     * @param featureIndices a subset of features that will be used to calculate the distance.
     * @return the nearest neighbor relative to the subset of features.
     */

    fun predict(query: Instance, featureIndices: List<Int>): Int {
        return trainingData.minByOrNull { instance ->
            euclideanDistance(query.features, instance.features, featureIndices)
        }?.classBelonged ?: error("Training data is empty")
    }

    /**
     * This is a flexible Euclidean distance calculation that allows calculation based on a subset of indices.
     * Such flexibility is helpful during later searching phrase, where we only consider a subset of features.
     *
     * @param a the first array
     * @param b the second array
     * @param indices the list of indices that will be considered during calculation
     *
     * @return the Euclidean distance relative to the choice of indices.
     */
    private fun euclideanDistance(a: DoubleArray, b: DoubleArray, indices: List<Int>): Double {
        return indices.sumOf { i -> (a[i] - b[i]).let { it * it } }
    }
}
