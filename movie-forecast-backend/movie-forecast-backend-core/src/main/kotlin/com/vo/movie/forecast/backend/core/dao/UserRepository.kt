package com.vo.movie.forecast.backend.core.dao

import com.vo.movie.forecast.backend.data.UserInfo
import com.vo.movie.forecast.commons.data.Locality
import com.vo.movie.forecast.commons.data.Movie

interface UserRepository {

    fun registerMovie(userId: Long, movie: Movie)

    fun updateLocality(userId: Long, locality: Locality)

    fun existsMovie(userId: Long, kinopoiskMovieId: Long): Boolean

    fun getUsersInfoWithLocality(page: Int, size: Int): List<UserInfo>

    fun getUsersIds(page: Int, size: Int): List<Long>
}