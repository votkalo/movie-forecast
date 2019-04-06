package com.vo.movie.forecast.backend.storage.core.util

import com.vo.movie.forecast.parser.dto.locality.LocalityDTO

fun List<LocalityDTO>.getLocalitiesLetters(): List<String> = this.map { it.name.first().toString() }.distinct().sorted()

fun List<LocalityDTO>.getLocalitiesNamesByLetter(letter: Char): List<String> =
    this.filter { it.name.first() == letter }.map { it.name }

fun List<LocalityDTO>.getLocalityByName(name: String): LocalityDTO = this.first { it.name == name }