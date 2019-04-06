package com.vo.movie.forecast.backend.storage.api

import com.vo.movie.forecast.parser.dto.locality.LocalityDTO

interface LocalityApi {

    fun getLocalities(): List<LocalityDTO>

    fun getLocalitiesLetters(): List<String>

    fun getLocalitiesNamesByLetter(letter: Char): List<String>

    fun getLocalityByName(name: String): LocalityDTO
}