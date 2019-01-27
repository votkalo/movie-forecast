package com.vo.movie.forecast.parser.provider

import com.vo.movie.forecast.parser.dto.Locality

interface LocalityProvider {

    fun getLocalities(): List<Locality>

    fun getLocalitiesLetters(): List<String>

    fun getLocalitiesByLetter(letter: Char): List<Locality>
}