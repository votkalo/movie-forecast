package com.vo.movie.forecast.backend.api.api

import com.vo.movie.forecast.commons.data.Locality
import com.vo.movie.forecast.commons.data.Movie

interface UserApi {

    fun registerMovie(userId: Long, movie: Movie)

    fun updateLocality(userId: Long, locality: Locality)

    fun existsMovie(userId: Long, kinopoiskMovieId: Long): Boolean
}