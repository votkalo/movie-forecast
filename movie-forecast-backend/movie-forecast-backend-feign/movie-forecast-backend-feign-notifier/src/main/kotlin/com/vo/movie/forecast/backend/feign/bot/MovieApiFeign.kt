package com.vo.movie.forecast.backend.feign.bot

import com.vo.movie.forecast.backend.api.notifier.MovieApi
import com.vo.movie.forecast.commons.data.MovieInfo
import feign.Param
import feign.RequestLine

interface MovieApiFeign : MovieApi {

    @RequestLine("GET /users/{userId}/movies?page={page}&size={size}")
    override fun getUserMovies(@Param("userId") userId: Long, @Param("page") page: Int, @Param("size") size: Int): List<MovieInfo>
}