package com.vo.movie.forecast.backend.core.service

import com.vo.movie.forecast.backend.data.MovieInfo

interface MovieService {

    fun getUserMovies(userId: Long, page: Int, size: Int): List<MovieInfo>
}