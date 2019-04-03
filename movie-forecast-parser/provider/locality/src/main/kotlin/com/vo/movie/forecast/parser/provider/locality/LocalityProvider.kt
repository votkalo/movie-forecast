package com.vo.movie.forecast.parser.provider.locality

import com.vo.movie.forecast.backend.storage.data.LocalityDTO

interface LocalityProvider {

    fun getLocalities(): List<LocalityDTO>

    fun getLocalitiesLetters(): List<String>

    fun getLocalitiesNamesByLetter(letter: Char): List<String>

    fun getLocalityByName(name: String): LocalityDTO
}