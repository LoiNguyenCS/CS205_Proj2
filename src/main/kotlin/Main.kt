package org.example

import org.example.ucr.loi.cs205.proj2.parser.DatasetParser
import org.example.ucr.loi.cs205.proj2.search.ForwardSearch
import java.io.File

fun main() {
    val file = File("src/dataset/CS205_small_Data__45.txt")
    val instances = DatasetParser.parse(file)

    println("Parsed ${instances.size} instances.")
    println("First instance: ${instances.firstOrNull()}")
    val forwardSearch = ForwardSearch()
    println(forwardSearch.selectFeatures(instances))
}
