package org.example.ucr.loi.cs205.proj2.instance

data class Instance (
    val classBelonged: Int,
    val features: DoubleArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Instance

        if (classBelonged != other.classBelonged) return false
        if (!features.contentEquals(other.features)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = classBelonged
        result = 31 * result + features.contentHashCode()
        return result
    }
}