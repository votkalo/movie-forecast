package com.vo.movie.forecast.backend.user.feign.bot

import com.vo.movie.forecast.backend.user.api.bot.UserApi
import com.vo.movie.forecast.commons.data.Locality
import feign.Param
import feign.RequestLine

interface UserApiFeign : UserApi {

    @RequestLine("PUT /users/{userId}/locality")
    override fun updateLocality(@Param("userId") userId: Long, locality: Locality)

    @RequestLine("DELETE /users/{userId}/locality")
    override fun removeLocality(@Param("userId") userId: Long)

}
