package com.vo.movie.forecast.backend.core.util

import com.vo.movie.forecast.backend.api.dto.User
import com.vo.movie.forecast.backend.core.entity.UserEntity

fun User.toEntity() = UserEntity(userId, chatId)

fun UserEntity.toDto() = User(userId, chatId)