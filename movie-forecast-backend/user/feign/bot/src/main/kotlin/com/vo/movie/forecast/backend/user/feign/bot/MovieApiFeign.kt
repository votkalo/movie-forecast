package com.vo.movie.forecast.backend.user.feign.bot

import com.vo.movie.forecast.backend.user.api.bot.MovieApi
import com.vo.movie.forecast.commons.data.Movie
import com.vo.movie.forecast.commons.data.MovieInfo
import feign.Param
import feign.RequestLine

interface MovieApiFeign : MovieApi {

    @RequestLine("POST /users/{userId}/movies")
    override fun registerMovie(movie: Movie, @Param("userId") userId: Long)

    @RequestLine("GET /users/{userId}/movies/{kinopoiskMovieId}/exists")
    override fun existsMovie(@Param("userId") userId: Long, @Param("kinopoiskMovieId") kinopoiskMovieId: Long): Boolean

    @RequestLine("GET /users/{userId}/movies/letters")
    override fun getMoviesLetters(@Param("userId") userId: Long): List<String>

    @RequestLine("GET /users/{userId}/movies/letters/{letter}")
    override fun getMoviesByLetter(@Param("userId") userId: Long, @Param("letter") letter: Char): List<MovieInfo>

    @RequestLine("POST /users/{userId}/movies/search")
    override fun searchMovie(movieInfo: MovieInfo, @Param("userId") userId: Long): Movie

    @RequestLine("DELETE /users/{userId}/movies/{kinopoiskMovieId}")
    override fun deleteMovie(@Param("userId") userId: Long, @Param("kinopoiskMovieId") kinopoiskMovieId: Long)
}