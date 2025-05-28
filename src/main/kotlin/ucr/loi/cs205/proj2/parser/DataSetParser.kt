package org.example.ucr.loi.cs205.proj2.parser

import org.example.ucr.loi.cs205.proj2.instance.Instance
import java.io.File

object DatasetParser {

    fun parse(file: File): List<Instance> = file.readLines()
        .asSequence()
        .filter { it.isNotBlank() }
        .mapIndexed { index, line ->
            val tokens = line.trim().split(Regex("\\s+"))
            if (tokens.size < 2)
                throw IllegalArgumentException("Invalid line at index $index: '${line.trim()}'")

            val classBelonged = tokens[0].toDouble().toInt()
            val features = tokens.drop(1).map(String::toDouble).toDoubleArray()

            Instance(classBelonged, features)
        }
        .toList()
}
