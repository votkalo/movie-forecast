package com.vo.movie.forecast.parser.feign.movie

import com.vo.movie.forecast.commons.data.Movie
import com.vo.movie.forecast.parser.api.movie.MovieApi
import com.vo.movie.forecast.parser.api.movie.dto.MovieSearchParams
import feign.Param
import feign.RequestLine

interface MovieApiFeign : MovieApi {

    @RequestLine("POST /movies/search")
    override fun searchMovie(searchParams: MovieSearchParams): List<Movie>

    @RequestLine("GET /movies/{kinopoiskMovieId}")
    override fun getMovie(@Param("kinopoiskMovieId") kinopoiskMovieId: Long): Movie
}