package com.vo.movie.forecast.backend.storage.feign

import com.vo.movie.forecast.backend.storage.api.MovieApi
import com.vo.movie.forecast.backend.storage.data.MovieDTO
import com.vo.movie.forecast.backend.storage.data.MovieSearchParamsDTO
import feign.RequestLine

interface MovieApiFeign : MovieApi {

    @RequestLine("POST /movies/search")
    override fun searchMovie(searchParams: MovieSearchParamsDTO): List<MovieDTO>
}