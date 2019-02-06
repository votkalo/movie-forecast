package com.vo.movie.forecast.backend.core.dao

import com.vo.movie.forecast.commons.dto.Locality
import com.vo.movie.forecast.commons.dto.Movie

interface UserRepository {

    fun registerMovie(userId: Long, movie: Movie)

    fun updateLocality(userId: Long, locality: Locality)

    fun existsMovie(userId: Long, kinopoiskMovieId: Long): Boolean
}