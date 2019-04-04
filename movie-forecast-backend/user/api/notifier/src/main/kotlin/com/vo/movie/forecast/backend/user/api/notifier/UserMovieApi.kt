package com.vo.movie.forecast.backend.user.api.notifier

import com.vo.movie.forecast.backend.storage.data.MovieDTO

interface UserMovieApi {

    fun getUserMovies(userId: Long, page: Int, size: Int): List<MovieDTO>
}