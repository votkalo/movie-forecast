package com.vo.movie.forecast.backend.user.api.bot

import com.vo.movie.forecast.backend.storage.data.MovieDTO
import com.vo.movie.forecast.backend.user.data.MovieFilterDTO

interface MovieApi {

    fun registerMovie(movie: MovieDTO, userId: Long)

    fun existsMovie(userId: Long, kinopoiskMovieId: Long): Boolean

    fun getMoviesLetters(userId: Long): List<String>

    fun getMoviesByLetter(userId: Long, letter: Char): List<MovieDTO>

    fun searchMovies(movieFilter: MovieFilterDTO, userId: Long): List<MovieDTO>

    fun deleteMovie(userId: Long, kinopoiskMovieId: Long)
}