package com.vo.movie.forecast.parser.feign.locality

import com.vo.movie.forecast.commons.data.Locality
import com.vo.movie.forecast.parser.api.locality.LocalityApi
import feign.RequestLine

interface LocalityApiFeign : LocalityApi {

    @RequestLine("GET /localities")
    override fun getLocalities(): List<Locality>
}