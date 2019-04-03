package com.vo.movie.forecast.backend.user.feign.bot

import com.vo.movie.forecast.backend.storage.data.LocalityDTO
import com.vo.movie.forecast.backend.user.api.bot.UserApi
import feign.Param
import feign.RequestLine

interface UserApiFeign : UserApi {

    @RequestLine("PUT /users/{userId}/locality")
    override fun updateLocality(@Param("userId") userId: Long, locality: LocalityDTO)

    @RequestLine("DELETE /users/{userId}/locality")
    override fun removeLocality(@Param("userId") userId: Long)

}
