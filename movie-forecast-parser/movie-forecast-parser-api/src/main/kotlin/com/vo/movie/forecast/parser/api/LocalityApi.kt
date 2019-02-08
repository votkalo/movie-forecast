package com.vo.movie.forecast.parser.api

import com.vo.movie.forecast.commons.data.Locality

interface LocalityApi {

    fun getLocalities(): List<Locality>
}