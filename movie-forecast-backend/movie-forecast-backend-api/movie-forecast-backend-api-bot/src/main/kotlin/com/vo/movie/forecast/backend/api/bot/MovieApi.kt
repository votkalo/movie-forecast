package com.vo.movie.forecast.backend.api.bot

import com.vo.movie.forecast.commons.data.Movie
import com.vo.movie.forecast.commons.data.MovieInfo

interface MovieApi {

    fun registerMovie(movie: Movie, userId: Long)

    fun existsMovie(userId: Long, kinopoiskMovieId: Long): Boolean

    fun getMoviesLetters(userId: Long): List<String>

    fun getMoviesByLetter(userId: Long, letter: Char): List<MovieInfo>

    fun searchMovie(movieInfo: MovieInfo, userId: Long): Movie

    fun deleteMovie(userId: Long, kinopoiskMovieId: Long)
}