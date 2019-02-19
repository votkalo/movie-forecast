package com.vo.movie.forecast.backend.core.dao

import com.vo.movie.forecast.backend.data.MovieInfo

interface MovieRepository {

    fun getUserMovies(userId: Long, page: Int, size: Int): List<MovieInfo>
}