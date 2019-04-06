package com.vo.movie.forecast.parser.feign.movie

import com.vo.movie.forecast.parser.api.movie.MovieApi
import com.vo.movie.forecast.parser.dto.movie.MovieDTO
import com.vo.movie.forecast.parser.dto.movie.MovieSearchParamsDTO
import feign.Param
import feign.RequestLine

interface MovieApiFeign : MovieApi {

    @RequestLine("POST /movies/search")
    override fun searchMovie(searchParams: MovieSearchParamsDTO): List<MovieDTO>

    @RequestLine("GET /movies/{kinopoiskMovieId}")
    override fun getMovie(@Param("kinopoiskMovieId") kinopoiskMovieId: Long): MovieDTO
}