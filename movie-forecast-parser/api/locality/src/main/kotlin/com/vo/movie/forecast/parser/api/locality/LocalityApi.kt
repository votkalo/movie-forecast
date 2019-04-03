package com.vo.movie.forecast.parser.api.locality

import com.vo.movie.forecast.backend.storage.data.LocalityDTO


interface LocalityApi {

    fun getLocalities(): List<LocalityDTO>
}