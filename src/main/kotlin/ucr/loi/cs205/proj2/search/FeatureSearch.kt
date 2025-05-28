package org.example.ucr.loi.cs205.proj2.search

import org.example.ucr.loi.cs205.proj2.instance.Instance

interface FeatureSearch {
    fun selectFeatures(instances: List<Instance>): List<Int>
}