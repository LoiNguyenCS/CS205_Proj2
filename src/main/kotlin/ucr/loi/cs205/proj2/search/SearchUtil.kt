package org.example.ucr.loi.cs205.proj2.search

import org.example.ucr.loi.cs205.proj2.classifier.NearestNeighborClassifier
import org.example.ucr.loi.cs205.proj2.instance.Instance

/**
 * This function performs leave-one-out cross validation to calculate the accuracy of a features choice
 * relative to a list of instances.
 *
 * @param instances a list of input instances.
 * @param featureIndices a subset of features.
 * @return the accuracy of that subset of features.
 */
fun crossValidationAccuracy(instances: List<Instance>, featureIndices: List<Int>): Double {
    var correct = 0
    for (i in instances.indices) {
        val test = instances[i]
        val training = instances.filterIndexed { j, _ -> i != j }
        val classifier = NearestNeighborClassifier(training)
        val predicted = classifier.predict(test, featureIndices)
        if (predicted == test.classBelonged) correct++
    }
    return String.format("%.4f", correct.toDouble() / instances.size * 100).toDouble()
}
