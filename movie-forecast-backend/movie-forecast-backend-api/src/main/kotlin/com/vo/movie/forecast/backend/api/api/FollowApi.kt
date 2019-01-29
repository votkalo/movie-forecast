package com.vo.movie.forecast.backend.api.api

import com.vo.movie.forecast.backend.api.dto.Follow

interface FollowApi {

    fun registerFollow(follow: Follow): Follow
}