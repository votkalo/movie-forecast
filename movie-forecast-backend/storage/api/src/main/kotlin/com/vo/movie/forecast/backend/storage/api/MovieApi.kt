package com.vo.movie.forecast.backend.storage.api

import com.vo.movie.forecast.backend.storage.data.MovieDTO
import com.vo.movie.forecast.backend.storage.data.MovieSearchParamsDTO

interface MovieApi {

    fun searchMovie(searchParams: MovieSearchParamsDTO): List<MovieDTO>

    fun getMovie(kinopoiskMovieId: Long): MovieDTO
}