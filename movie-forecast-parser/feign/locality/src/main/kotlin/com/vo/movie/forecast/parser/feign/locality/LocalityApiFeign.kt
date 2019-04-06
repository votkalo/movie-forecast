package com.vo.movie.forecast.parser.feign.locality

import com.vo.movie.forecast.parser.api.locality.LocalityApi
import com.vo.movie.forecast.parser.dto.locality.LocalityDTO
import feign.RequestLine

interface LocalityApiFeign : LocalityApi {

    @RequestLine("GET /localities")
    override fun getLocalities(): List<LocalityDTO>
}