package com.vo.movie.forecast.backend.core.service.impl

import com.vo.movie.forecast.backend.core.dao.MovieRepository
import com.vo.movie.forecast.backend.core.service.MovieService
import com.vo.movie.forecast.commons.data.Movie
import com.vo.movie.forecast.commons.data.MovieInfo
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
open class MovieServiceImpl(private val movieRepository: MovieRepository) : MovieService {

    @Transactional
    override fun registerMovie(userId: Long, movie: Movie) {
        movieRepository.registerMovie(userId, movie)
        movieRepository.registerMovieLetter(userId, movie.title.toUpperCase().first().toString())
    }

    override fun existsMovie(userId: Long, kinopoiskMovieId: Long): Boolean = movieRepository.existsMovie(userId, kinopoiskMovieId)

    override fun getMovies(userId: Long, page: Int, size: Int): List<MovieInfo> = movieRepository.getMovies(userId, page, size)

    override fun getMoviesLetters(userId: Long): List<String> = movieRepository.getMoviesLetters(userId)

    override fun getMoviesByLetter(userId: Long, letter: Char): List<MovieInfo> = movieRepository.getMoviesByLetter(userId, letter)

    override fun searchMovie(userId: Long, movieInfo: MovieInfo): Movie = movieRepository.searchMovie(userId, movieInfo)

    override fun deleteMovie(userId: Long, kinopoiskMovieId: Long) {
        val movie = movieRepository.getMovie(userId, kinopoiskMovieId)
        movieRepository.deleteMovie(userId, kinopoiskMovieId)
        val movieLetter = movie.title.first().toUpperCase()
        if (movieRepository.getMoviesByLetter(userId, movieLetter).isEmpty()) {
            movieRepository.deleteMovieLetter(userId, movieLetter.toString())
        }
    }
}