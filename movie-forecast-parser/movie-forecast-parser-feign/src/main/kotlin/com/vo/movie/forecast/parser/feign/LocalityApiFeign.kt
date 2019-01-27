package com.vo.movie.forecast.parser.feign

import com.vo.movie.forecast.parser.api.LocalityApi
import com.vo.movie.forecast.parser.dto.Locality
import feign.RequestLine

interface LocalityApiFeign : LocalityApi {

    @RequestLine("GET /localities")
    override fun getLocalities(): List<Locality>
}