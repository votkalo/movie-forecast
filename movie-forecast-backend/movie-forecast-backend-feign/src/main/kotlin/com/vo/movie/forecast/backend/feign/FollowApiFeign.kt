package com.vo.movie.forecast.backend.feign

import com.vo.movie.forecast.backend.api.api.FollowApi
import com.vo.movie.forecast.backend.api.dto.Follow
import feign.Param
import feign.RequestLine

interface FollowApiFeign : FollowApi {

    @RequestLine("POST /follows")
    override fun registerFollow(follow: Follow): Follow
}