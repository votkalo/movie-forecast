package com.vo.movie.forecast.backend.storage.core.service

import com.vo.movie.forecast.backend.storage.data.MovieDTO
import com.vo.movie.forecast.backend.storage.data.MovieSearchParamsDTO

interface MovieService {

    fun searchMovie(searchParams: MovieSearchParamsDTO): List<MovieDTO>
}