package com.vo.movie.forecast.backend.api.api

interface UserApi {

    fun registerMovie(userId: Long, kinopoiskMovieId: Long)
}