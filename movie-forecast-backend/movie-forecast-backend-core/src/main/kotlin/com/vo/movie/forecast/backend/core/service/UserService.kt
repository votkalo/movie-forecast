package com.vo.movie.forecast.backend.core.service

import com.vo.movie.forecast.commons.dto.Locality

interface UserService {

    fun registerMovie(userId: Long, kinopoiskMovieId: Long)

    fun updateLocality(userId: Long, locality: Locality)
}