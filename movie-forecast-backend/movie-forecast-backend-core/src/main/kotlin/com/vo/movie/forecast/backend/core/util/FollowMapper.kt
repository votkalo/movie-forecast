package com.vo.movie.forecast.backend.core.util

import com.vo.movie.forecast.backend.api.dto.Follow
import com.vo.movie.forecast.backend.core.entity.FollowEntity

fun Follow.toEntity() = FollowEntity(
        telegramUserId = telegramUserId,
        kinopoiskMovieId = kinopoiskMovieId
)

fun FollowEntity.toDto() = Follow(telegramUserId, kinopoiskMovieId)