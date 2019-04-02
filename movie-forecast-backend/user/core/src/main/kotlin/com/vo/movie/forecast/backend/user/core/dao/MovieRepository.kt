package com.vo.movie.forecast.backend.user.core.dao

import com.vo.movie.forecast.commons.data.Movie
import com.vo.movie.forecast.commons.data.MovieInfo

interface MovieRepository {

    fun registerMovie(userId: Long, movie: Movie)

    fun registerMovieLetter(userId: Long, movieLetter: String)

    fun existsMovie(userId: Long, kinopoiskMovieId: Long): Boolean

    fun getMovie(userId: Long, kinopoiskMovieId: Long): MovieInfo

    fun getMovies(userId: Long, page: Int, size: Int): List<MovieInfo>

    fun getMoviesLetters(userId: Long): List<String>

    fun getMoviesByLetter(userId: Long, letter: Char): List<MovieInfo>

    fun searchMovie(userId: Long, movieInfo: MovieInfo): Movie

    fun deleteMovie(userId: Long, kinopoiskMovieId: Long)

    fun deleteMovieLetter(userId: Long, movieLetter: String)
}