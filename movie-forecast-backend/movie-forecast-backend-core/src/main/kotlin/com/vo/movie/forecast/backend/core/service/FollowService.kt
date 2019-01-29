package com.vo.movie.forecast.backend.core.service

import com.vo.movie.forecast.backend.api.dto.Follow

interface FollowService {

    fun registerFollow(follow: Follow): Follow
}