package com.vo.movie.forecast.backend.api.api

import com.vo.movie.forecast.commons.dto.Locality
import com.vo.movie.forecast.commons.dto.Movie

interface UserApi {

    fun registerMovie(userId: Long, movie: Movie)

    fun updateLocality(userId: Long, locality: Locality)

    fun existsMovie(userId: Long, kinopoiskMovieId: Long): Boolean
}