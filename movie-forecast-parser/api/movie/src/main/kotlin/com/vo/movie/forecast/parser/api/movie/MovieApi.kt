package com.vo.movie.forecast.parser.api.movie

import com.vo.movie.forecast.parser.dto.movie.MovieDTO
import com.vo.movie.forecast.parser.dto.movie.MovieSearchParamsDTO

interface MovieApi {

    fun searchMovie(searchParams: MovieSearchParamsDTO): List<MovieDTO>

    fun getMovie(kinopoiskMovieId: Long): MovieDTO
}