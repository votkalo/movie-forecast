package com.vo.movie.forecast.parser.api.movie

import com.vo.movie.forecast.backend.storage.data.MovieDTO
import com.vo.movie.forecast.parser.api.movie.dto.MovieSearchParamsDTO

interface MovieApi {

    fun searchMovie(searchParams: MovieSearchParamsDTO): List<MovieDTO>

    fun getMovie(kinopoiskMovieId: Long): MovieDTO
}