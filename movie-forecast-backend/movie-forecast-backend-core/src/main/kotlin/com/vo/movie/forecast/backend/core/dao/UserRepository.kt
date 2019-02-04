package com.vo.movie.forecast.backend.core.dao

import com.vo.movie.forecast.commons.dto.Locality

interface UserRepository {

    fun registerMovie(userId: Long, kinopoiskMovieId: Long)
    fun updateLocality(userId: Long, locality: Locality)
}