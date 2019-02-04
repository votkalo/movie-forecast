package com.vo.movie.forecast.backend.core.dao

interface UserRepository {

    fun registerMovie(userId: Long, kinopoiskMovieId: Long)
}