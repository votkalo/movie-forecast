package com.vo.movie.forecast.parser.provider

import com.vo.movie.forecast.commons.dto.Movie
import com.vo.movie.forecast.parser.dto.MovieSearchParams

interface MovieProvider {

    fun searchMovie(searchParams: MovieSearchParams): List<Movie>

    fun getMovie(kinopoiskMovieId: Long): Movie
}