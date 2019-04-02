package com.vo.movie.forecast.parser.provider.locality.util

import com.vo.movie.forecast.commons.data.Locality

fun List<Locality>.getLocalitiesLetters(): List<String> = this
        .map { it.name.first().toString() }
        .distinct()
        .sorted()

fun List<Locality>.getLocalitiesNamesByLetter(letter: Char): List<String> = this
        .filter { it.name.first() == letter }
        .map { it.name }

fun List<Locality>.getLocalityByName(name: String): Locality = this.first { it.name == name }