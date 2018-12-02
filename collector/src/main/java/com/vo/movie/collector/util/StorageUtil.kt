package com.vo.movie.collector.util

import com.vo.movie.collector.configuration.StorageProperties
import java.nio.file.Files
import java.nio.file.Paths

fun StorageProperties.File.createPathIfNotExist() {
    val path = Paths.get(path)
    Files.createDirectories(path)
}