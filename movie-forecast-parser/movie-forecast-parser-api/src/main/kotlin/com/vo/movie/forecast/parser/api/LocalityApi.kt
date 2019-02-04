package com.vo.movie.forecast.parser.api

import com.vo.movie.forecast.commons.dto.Locality

interface LocalityApi {

    fun getLocalities(): List<Locality>
}