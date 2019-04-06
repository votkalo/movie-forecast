package com.vo.movie.forecast.backend.storage.core.service

import com.vo.movie.forecast.parser.dto.movie.MovieDTO
import com.vo.movie.forecast.parser.dto.movie.MovieSearchParamsDTO

interface MovieService {

    fun searchMovie(searchParams: MovieSearchParamsDTO): List<MovieDTO>

    fun getMovie(kinopoiskMovieId: Long): MovieDTO
}