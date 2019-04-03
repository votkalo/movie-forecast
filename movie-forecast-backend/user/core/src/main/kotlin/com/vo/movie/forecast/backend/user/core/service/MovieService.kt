package com.vo.movie.forecast.backend.user.core.service

import com.vo.movie.forecast.backend.storage.data.MovieDTO
import com.vo.movie.forecast.backend.user.data.MovieFilterDTO

interface MovieService {

    fun registerMovie(userId: Long, movie: MovieDTO)

    fun existsMovie(userId: Long, kinopoiskMovieId: Long): Boolean

    fun getMovies(userId: Long, page: Int, size: Int): List<MovieDTO>

    fun getMoviesLetters(userId: Long): List<String>

    fun getMoviesByLetter(userId: Long, letter: Char): List<MovieDTO>

    fun searchMovies(userId: Long, movieFilter: MovieFilterDTO): List<MovieDTO>

    fun deleteMovie(userId: Long, kinopoiskMovieId: Long)
}