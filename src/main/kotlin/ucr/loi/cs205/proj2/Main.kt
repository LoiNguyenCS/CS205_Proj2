package org.example.ucr.loi.cs205.proj2

import org.example.ucr.loi.cs205.proj2.parser.DatasetParser
import org.example.ucr.loi.cs205.proj2.search.BackwardElimination
import org.example.ucr.loi.cs205.proj2.search.ForwardSearch
import org.example.ucr.loi.cs205.proj2.search.FeatureSearch
import org.example.ucr.loi.cs205.proj2.search.crossValidationAccuracy
import ucr.loi.cs205.proj2.parser.moveClassLabelToFront
import java.io.File

fun main() {
    println("Type in the name of the file to test: (pick one in the dataset folder) ")
    val fileName = readlnOrNull()?.trim()
    val fileNameWithPath =  "src/dataset/$fileName"
    if (fileName.isNullOrEmpty() || !File(fileNameWithPath).exists()) {
        println("Invalid file.")
        return
    }

    println("Type the number of the algorithm you want to run.")
    println("1) Forward Selection")
    println("2) Backward Elimination")

    val choice = readlnOrNull()?.toIntOrNull()
    val selector: FeatureSearch = when (choice) {
        1 -> ForwardSearch()
        2 -> BackwardElimination()
        else -> {
            println("Invalid choice. We are over!")
            return
        }
    }

    val instances = DatasetParser.parse(File(fileNameWithPath))
    selector.selectFeatures(instances)
}
