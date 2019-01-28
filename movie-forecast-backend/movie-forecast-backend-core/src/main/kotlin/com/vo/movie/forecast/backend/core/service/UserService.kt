package com.vo.movie.forecast.backend.core.service

import com.vo.movie.forecast.backend.api.dto.User

interface UserService {

    fun registerUser(user: User): User

    fun isUserExist(userId: Long): Boolean
}