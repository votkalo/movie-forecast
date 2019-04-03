package com.vo.movie.forecast.backend.user.feign.bot

import com.vo.movie.forecast.backend.storage.data.MovieDTO
import com.vo.movie.forecast.backend.user.api.bot.MovieApi
import com.vo.movie.forecast.backend.user.data.MovieFilterDTO
import feign.Param
import feign.RequestLine

interface MovieApiFeign : MovieApi {

    @RequestLine("POST /users/{userId}/movies")
    override fun registerMovie(movie: MovieDTO, @Param("userId") userId: Long)

    @RequestLine("GET /users/{userId}/movies/{kinopoiskMovieId}/exists")
    override fun existsMovie(@Param("userId") userId: Long, @Param("kinopoiskMovieId") kinopoiskMovieId: Long): Boolean

    @RequestLine("GET /users/{userId}/movies/letters")
    override fun getMoviesLetters(@Param("userId") userId: Long): List<String>

    @RequestLine("GET /users/{userId}/movies/letters/{letter}")
    override fun getMoviesByLetter(@Param("userId") userId: Long, @Param("letter") letter: Char): List<MovieDTO>

    @RequestLine("POST /users/{userId}/movies/search")
    override fun searchMovies(movieFilter: MovieFilterDTO, @Param("userId") userId: Long): List<MovieDTO>

    @RequestLine("DELETE /users/{userId}/movies/{kinopoiskMovieId}")
    override fun deleteMovie(@Param("userId") userId: Long, @Param("kinopoiskMovieId") kinopoiskMovieId: Long)
}