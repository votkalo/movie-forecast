package com.vo.movie.collector.util

fun String.removeLine(line: String): String = replace(line, "")

fun String.removeLines(lines: List<String>): String {
    var newLine: String = this
    lines.forEach { newLine = newLine.removeLine(it) }
    return newLine
}