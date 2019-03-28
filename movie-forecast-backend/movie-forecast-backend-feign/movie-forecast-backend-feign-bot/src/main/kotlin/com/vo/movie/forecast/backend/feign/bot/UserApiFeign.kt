package com.vo.movie.forecast.backend.feign.bot

import com.vo.movie.forecast.backend.api.bot.UserApi
import com.vo.movie.forecast.commons.data.Locality
import feign.Param
import feign.RequestLine

interface UserApiFeign : UserApi {

    @RequestLine("PUT /users/{userId}/updateLocality")
    override fun updateLocality(@Param("userId") userId: Long, locality: Locality)
}
