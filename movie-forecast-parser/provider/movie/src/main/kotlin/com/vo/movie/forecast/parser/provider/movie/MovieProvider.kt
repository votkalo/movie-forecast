package com.vo.movie.forecast.parser.provider.movie

import com.vo.movie.forecast.backend.storage.data.MovieDTO
import com.vo.movie.forecast.parser.api.movie.dto.MovieSearchParamsDTO

interface MovieProvider {

    fun searchMovie(searchParams: MovieSearchParamsDTO): List<MovieDTO>

    fun getMovie(kinopoiskMovieId: Long): MovieDTO
}