package com.vo.movie.forecast.backend.api.notifier

import com.vo.movie.forecast.commons.data.MovieInfo

interface MovieApi {

    fun getUserMovies(userId: Long, page: Int, size: Int): List<MovieInfo>
}