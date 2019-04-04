package com.vo.movie.forecast.backend.user.api.bot

import com.vo.movie.forecast.backend.storage.data.MovieDTO

interface UserMovieApi {

    fun registerMovie(movie: MovieDTO, userId: Long)

    fun existsMovie(userId: Long, kinopoiskMovieId: Long): Boolean

    fun getMoviesLetters(userId: Long): List<String>

    fun getMoviesByLetter(userId: Long, letter: Char): List<MovieDTO>

    fun deleteMovie(userId: Long, kinopoiskMovieId: Long)
}