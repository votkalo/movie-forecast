package com.vo.movie.forecast.parser.provider.util

import com.vo.movie.forecast.commons.dto.Locality

fun getLocalitiesLetters(localities: List<Locality>): List<String> = localities
        .map { it.name.first().toString() }
        .distinct()
        .sorted()

fun getLocalitiesNamesByLetter(localities: List<Locality>, letter: Char): List<String> = localities
        .filter { it.name.first() == letter }
        .map { it.name }

fun getLocalityByName(localities: List<Locality>, name: String): Locality = localities.first { it.name == name }