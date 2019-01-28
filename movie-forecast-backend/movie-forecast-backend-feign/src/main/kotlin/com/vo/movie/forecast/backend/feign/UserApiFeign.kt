package com.vo.movie.forecast.backend.feign

import com.vo.movie.forecast.backend.api.api.UserApi
import com.vo.movie.forecast.backend.api.dto.User
import feign.Param
import feign.RequestLine

interface UserApiFeign : UserApi {

    @RequestLine("POST /users")
    override fun registerUser(user: User): User

    @RequestLine("GET /users/{userId}/exists")
    override fun isUserExist(@Param("userId") userId: Long): Boolean
}