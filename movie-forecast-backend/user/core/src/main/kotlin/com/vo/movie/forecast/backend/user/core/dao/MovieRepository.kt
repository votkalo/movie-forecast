package com.vo.movie.forecast.backend.user.core.dao

import com.vo.movie.forecast.backend.user.core.document.Movie

interface MovieRepository {

    fun registerMovie(userId: Long, movie: Movie)

    fun registerMovieLetter(userId: Long, movieLetter: String)

    fun existsMovie(userId: Long, kinopoiskMovieId: Long): Boolean

    fun getMovie(userId: Long, kinopoiskMovieId: Long): Movie

    fun getMovies(userId: Long, page: Int, size: Int): List<Movie>

    fun getMoviesLetters(userId: Long): List<String>

    fun getMoviesByLetter(userId: Long, letter: Char): List<Movie>

    fun deleteMovie(userId: Long, kinopoiskMovieId: Long)

    fun deleteMovieLetter(userId: Long, movieLetter: String)
}