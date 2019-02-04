package com.vo.movie.forecast.backend.core.service

interface UserService {

    fun registerMovie(userId: Long, kinopoiskMovieId: Long)
}