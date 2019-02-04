package com.vo.movie.forecast.backend.feign

import com.vo.movie.forecast.backend.api.api.UserApi
import com.vo.movie.forecast.commons.dto.Locality
import feign.Param
import feign.RequestLine

interface UserApiFeign : UserApi {

    @RequestLine("PUT /users/{userId}/registerMovie?kinopoiskMovieId={kinopoiskMovieId}")
    override fun registerMovie(@Param("userId") userId: Long, @Param("kinopoiskMovieId") kinopoiskMovieId: Long)

    @RequestLine("PUT /users/{userId}/updateLocality")
    override fun updateLocality(@Param("userId") userId: Long, locality: Locality)
}