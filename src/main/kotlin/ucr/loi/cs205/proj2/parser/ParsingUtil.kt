package ucr.loi.cs205.proj2.parser

import java.io.File

/**
 * Reorders a dataset so that the class label (assumed to be the last column)
 * is moved to the front of each line.
 *
 * @param inputPath the path of the input file
 * @return the path to the temporary file containing the transformed dataset
 */
fun moveClassLabelToFront(inputPath: String): String {
    val inputFile = File(inputPath)
    val tempFile = File.createTempFile("reordered_dataset_", ".txt")

    inputFile.readLines()
        .filter { it.isNotBlank() }
        .map { line ->
            val tokens = line.trim().split(Regex("\\s+"))
            val reordered = listOf(tokens.last()) + tokens.dropLast(1)
            reordered.joinToString(" ")
        }.let { lines ->
            tempFile.printWriter().use { out -> lines.forEach(out::println) }
        }

    return tempFile.absolutePath
}
