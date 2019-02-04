package com.vo.movie.forecast.backend.feign

import com.vo.movie.forecast.backend.api.api.UserApi
import feign.Param
import feign.RequestLine

interface UserApiFeign : UserApi {

    @RequestLine("POST /users/{userId}/registerMovie?kinopoiskMovieId={kinopoiskMovieId}")
    override fun registerMovie(@Param("userId") userId: Long, @Param("kinopoiskMovieId") kinopoiskMovieId: Long)
}