package com.vo.movie.forecast.parser.feign

import com.vo.movie.forecast.parser.api.MovieForecastParserApi
import com.vo.movie.forecast.parser.dto.Movie
import com.vo.movie.forecast.parser.dto.MovieSearchParams
import feign.RequestLine

interface MovieForecastParserApiFeign : MovieForecastParserApi {

    @RequestLine("POST /movies/search")
    override fun searchMovie(searchParams: MovieSearchParams): List<Movie>
}