package com.vo.movie.forecast.backend.user.feign.notifier

import com.vo.movie.forecast.backend.user.data.UserInfo
import com.vo.movie.forecast.backend.user.api.notifier.UserApi
import feign.Param
import feign.RequestLine

interface UserApiFeign : UserApi {

    @RequestLine("GET /users?page={page}&size={size}")
    override fun getUsersInfoWithLocality(@Param("page") page: Int, @Param("size") size: Int): List<UserInfo>

    @RequestLine("GET /users/ids?page={page}&size={size}")
    override fun getUsersIds(@Param("page") page: Int, @Param("size") size: Int): List<Long>
}