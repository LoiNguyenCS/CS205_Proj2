package org.example.ucr.loi.cs205.proj2.search

import org.example.ucr.loi.cs205.proj2.classifier.NearestNeighborClassifier
import org.example.ucr.loi.cs205.proj2.instance.Instance

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
