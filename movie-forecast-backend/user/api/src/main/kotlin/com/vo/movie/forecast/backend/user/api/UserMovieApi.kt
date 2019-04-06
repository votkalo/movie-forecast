package com.vo.movie.forecast.backend.user.api

import com.vo.movie.forecast.parser.dto.movie.MovieDTO


interface UserMovieApi {

    fun registerMovie(movie: MovieDTO, userId: Long)

    fun existsMovie(userId: Long, kinopoiskMovieId: Long): Boolean

    fun getMoviesLetters(userId: Long): List<String>

    fun getMoviesByLetter(userId: Long, letter: Char): List<MovieDTO>

    fun deleteMovie(userId: Long, kinopoiskMovieId: Long)

    fun getUserMovies(userId: Long, page: Int, size: Int): List<MovieDTO>
}