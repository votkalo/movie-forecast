package com.vo.movie.forecast.parser.api.locality

import com.vo.movie.forecast.parser.dto.locality.LocalityDTO

interface LocalityApi {

    fun getLocalities(): List<LocalityDTO>
}