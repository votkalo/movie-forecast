package com.vo.movie.forecast.backend.storage.core.service

import com.vo.movie.forecast.backend.storage.data.LocalityDTO

interface LocalityService {

    fun getLocalities(): List<LocalityDTO>

    fun getLocalitiesLetters(): List<String>

    fun getLocalitiesNamesByLetter(letter: Char): List<String>

    fun getLocalityByName(name: String): LocalityDTO
}