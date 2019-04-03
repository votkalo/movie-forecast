package com.vo.movie.forecast.parser.feign.locality

import com.vo.movie.forecast.backend.storage.data.LocalityDTO
import com.vo.movie.forecast.parser.api.locality.LocalityApi
import feign.RequestLine

interface LocalityApiFeign : LocalityApi {

    @RequestLine("GET /localities")
    override fun getLocalities(): List<LocalityDTO>
}