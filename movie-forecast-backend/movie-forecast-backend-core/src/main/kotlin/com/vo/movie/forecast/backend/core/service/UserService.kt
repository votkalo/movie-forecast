package com.vo.movie.forecast.backend.core.service

import com.vo.movie.forecast.commons.data.Locality
import com.vo.movie.forecast.commons.data.Movie

interface UserService {

    fun registerMovie(userId: Long, movie: Movie)

    fun updateLocality(userId: Long, locality: Locality)

    fun existsMovie(userId: Long, kinopoiskMovieId: Long): Boolean

}