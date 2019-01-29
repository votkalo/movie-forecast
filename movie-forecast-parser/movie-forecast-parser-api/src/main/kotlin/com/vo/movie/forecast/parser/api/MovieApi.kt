package com.vo.movie.forecast.parser.api

import com.vo.movie.forecast.parser.dto.Movie
import com.vo.movie.forecast.parser.dto.MovieSearchParams

interface MovieApi {

    fun searchMovie(searchParams: MovieSearchParams): List<Movie>
}