package com.vo.movie.forecast.backend.feign.bot

import com.vo.movie.forecast.backend.api.notifier.UserApi
import com.vo.movie.forecast.backend.data.User
import feign.Param
import feign.RequestLine

interface UserApiFeign : UserApi {

    @RequestLine("GET /users?page={page}&size={size}")
    override fun getUsers(@Param("page") page: Int, @Param("size") size: Int): List<User>
}