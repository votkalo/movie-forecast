package com.vo.movie.forecast.parser.provider.locality

import com.vo.movie.forecast.commons.data.Locality

interface LocalityProvider {

    fun getLocalities(): List<Locality>

    fun getLocalitiesLetters(): List<String>

    fun getLocalitiesNamesByLetter(letter: Char): List<String>

    fun getLocalityByName(name: String): Locality
}