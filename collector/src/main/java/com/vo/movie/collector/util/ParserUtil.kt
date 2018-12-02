package com.vo.movie.collector.util

import com.vo.movie.collector.configuration.ParserProperties

fun ParserProperties.Resource.Element.parseValues(line: String): List<String> {
    return line
        .removeLines(this.unusedLines)
        .split(this.delimiter)
        .filter { it.isNotBlank() }
        .map { it.trim() }
}

fun ParserProperties.Resource.createFullURL(postfix: String): String = url.toString() + postfix