package com.vo.movie.forecast.parser.feign

import com.vo.movie.forecast.commons.data.Movie
import com.vo.movie.forecast.parser.api.MovieApi
import com.vo.movie.forecast.parser.dto.MovieSearchParams
import feign.Param
import feign.RequestLine

interface MovieApiFeign : MovieApi {

    @RequestLine("POST /movies/search")
    override fun searchMovie(searchParams: MovieSearchParams): List<Movie>

    @RequestLine("GET /movies/{kinopoiskMovieId}")
    override fun getMovie(@Param("kinopoiskMovieId") kinopoiskMovieId: Long): Movie
}