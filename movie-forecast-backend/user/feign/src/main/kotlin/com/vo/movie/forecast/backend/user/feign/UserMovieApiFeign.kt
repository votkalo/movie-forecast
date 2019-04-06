package com.vo.movie.forecast.backend.user.feign

import com.vo.movie.forecast.backend.user.api.UserMovieApi
import com.vo.movie.forecast.parser.dto.movie.MovieDTO
import feign.Param
import feign.RequestLine

interface UserMovieApiFeign : UserMovieApi {

    @RequestLine("POST /users/{userId}/movies")
    override fun registerMovie(movie: MovieDTO, @Param("userId") userId: Long)

    @RequestLine("GET /users/{userId}/movies/{kinopoiskMovieId}/exists")
    override fun existsMovie(@Param("userId") userId: Long, @Param("kinopoiskMovieId") kinopoiskMovieId: Long): Boolean

    @RequestLine("GET /users/{userId}/movies/letters")
    override fun getMoviesLetters(@Param("userId") userId: Long): List<String>

    @RequestLine("GET /users/{userId}/movies/letters/{letter}")
    override fun getMoviesByLetter(@Param("userId") userId: Long, @Param("letter") letter: Char): List<MovieDTO>

    @RequestLine("DELETE /users/{userId}/movies/{kinopoiskMovieId}")
    override fun deleteMovie(@Param("userId") userId: Long, @Param("kinopoiskMovieId") kinopoiskMovieId: Long)

    @RequestLine("GET /users/{userId}/movies?page={page}&size={size}")
    override fun getUserMovies(@Param("userId") userId: Long, @Param("page") page: Int, @Param("size") size: Int): List<MovieDTO>
}