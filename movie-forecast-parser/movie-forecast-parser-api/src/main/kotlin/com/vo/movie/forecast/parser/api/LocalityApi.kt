package com.vo.movie.forecast.parser.api

import com.vo.movie.forecast.parser.dto.Locality

interface LocalityApi {

    fun getLocalities(): List<Locality>
}