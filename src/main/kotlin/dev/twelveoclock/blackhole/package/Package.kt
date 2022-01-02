package dev.twelveoclock.blackhole.`package`

import java.lang.Package

data class Package(
    val name: String,
    val version: String,
    val dependencies: Map<String, Package>,
    val isExperimental: Boolean,
    val downloadURL: String,
)