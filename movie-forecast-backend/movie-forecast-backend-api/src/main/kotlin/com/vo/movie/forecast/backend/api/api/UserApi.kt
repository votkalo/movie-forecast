package com.vo.movie.forecast.backend.api.api

import com.vo.movie.forecast.backend.api.dto.User

interface UserApi {

    fun registerUser(user: User): User

    fun isUserExist(userId: Long): Boolean
}