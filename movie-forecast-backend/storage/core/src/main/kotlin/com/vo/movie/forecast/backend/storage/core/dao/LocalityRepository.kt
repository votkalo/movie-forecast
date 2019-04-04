package com.vo.movie.forecast.backend.storage.core.dao

import com.vo.movie.forecast.backend.storage.core.document.Locality

interface LocalityRepository {

    fun getLocalities(): List<Locality>

    fun saveLocality(locality: Locality)

    fun getLocalitiesLetters(): List<String>

    fun getLocalitiesNamesByLetter(letter: Char): List<String>

    fun getLocalityByName(name: String): Locality?
}