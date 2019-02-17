package com.vo.movie.forecast.parser.provider.movie

import com.vo.movie.forecast.commons.data.Movie
import com.vo.movie.forecast.parser.api.movie.dto.MovieSearchParams

interface MovieProvider {

    fun searchMovie(searchParams: MovieSearchParams): List<Movie>

    fun getMovie(kinopoiskMovieId: Long): Movie
}