package com.vo.movie.forecast.backend.api.api

import com.vo.movie.forecast.commons.dto.Locality

interface UserApi {

    fun registerMovie(userId: Long, kinopoiskMovieId: Long)

    fun updateLocality(userId: Long, locality: Locality)
}