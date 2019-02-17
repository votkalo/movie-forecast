package com.vo.movie.forecast.backend.core.dao

import com.vo.movie.forecast.backend.data.User
import com.vo.movie.forecast.commons.data.Locality
import com.vo.movie.forecast.commons.data.Movie

interface UserRepository {

    fun registerMovie(userId: Long, movie: Movie)

    fun updateLocality(userId: Long, locality: Locality)

    fun existsMovie(userId: Long, kinopoiskMovieId: Long): Boolean

    fun getUsers(page: Int, size: Int): List<User>
}