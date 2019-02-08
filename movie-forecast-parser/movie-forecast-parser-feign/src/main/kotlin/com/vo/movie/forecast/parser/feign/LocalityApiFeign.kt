package com.vo.movie.forecast.parser.feign

import com.vo.movie.forecast.commons.data.Locality
import com.vo.movie.forecast.parser.api.LocalityApi
import feign.RequestLine

interface LocalityApiFeign : LocalityApi {

    @RequestLine("GET /localities")
    override fun getLocalities(): List<Locality>
}