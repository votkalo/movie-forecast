package com.vo.movie.forecast.backend.storage.feign

import com.vo.movie.forecast.backend.storage.api.LocalityApi
import com.vo.movie.forecast.backend.storage.data.LocalityDTO
import feign.Param
import feign.RequestLine

interface LocalityApiFeign : LocalityApi {

    @RequestLine("GET /localities")
    override fun getLocalities(): List<LocalityDTO>

    @RequestLine("GET /localities/letters")
    override fun getLocalitiesLetters(): List<String>

    @RequestLine("GET /localities/names/{letter}")
    override fun getLocalitiesNamesByLetter(@Param("letter") letter: Char): List<String>

    @RequestLine("GET /localities/{name}")
    override fun getLocalityByName(@Param("name") name: String): LocalityDTO
}