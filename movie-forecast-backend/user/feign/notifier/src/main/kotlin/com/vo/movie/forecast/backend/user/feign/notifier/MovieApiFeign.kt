package com.vo.movie.forecast.backend.user.feign.notifier

import com.vo.movie.forecast.backend.storage.data.MovieDTO
import com.vo.movie.forecast.backend.user.api.notifier.MovieApi
import feign.Param
import feign.RequestLine

interface MovieApiFeign : MovieApi {

    @RequestLine("GET /users/{userId}/movies?page={page}&size={size}")
    override fun getUserMovies(@Param("userId") userId: Long, @Param("page") page: Int, @Param("size") size: Int): List<MovieDTO>
}