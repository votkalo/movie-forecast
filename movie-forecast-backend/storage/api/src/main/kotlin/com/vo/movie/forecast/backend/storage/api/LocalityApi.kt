package com.vo.movie.forecast.backend.storage.api

import com.vo.movie.forecast.backend.storage.data.LocalityDTO

interface LocalityApi {

    fun getLocalities(): List<LocalityDTO>

    fun getLocalitiesLetters(): List<String>

    fun getLocalitiesNamesByLetter(letter: Char): List<String>

    fun getLocalityByName(name: String): LocalityDTO
}