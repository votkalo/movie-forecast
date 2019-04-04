package com.vo.movie.forecast.backend.user.core.service.impl

import com.vo.movie.forecast.backend.storage.data.MovieDTO
import com.vo.movie.forecast.backend.user.core.converter.toDTO
import com.vo.movie.forecast.backend.user.core.converter.toEntity
import com.vo.movie.forecast.backend.user.core.dao.MovieRepository
import com.vo.movie.forecast.backend.user.core.service.MovieService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
open class MovieServiceImpl(private val movieRepository: MovieRepository) : MovieService {

    @Transactional
    override fun registerMovie(userId: Long, movie: MovieDTO) {
        movieRepository.registerMovie(userId, movie.toEntity())
        movieRepository.registerMovieLetter(userId, movie.title.toUpperCase().first().toString())
    }

    override fun existsMovie(userId: Long, kinopoiskMovieId: Long): Boolean =
        movieRepository.existsMovie(userId, kinopoiskMovieId)

    override fun getMovies(userId: Long, page: Int, size: Int): List<MovieDTO> =
        movieRepository.getMovies(userId, page, size).map { it.toDTO() }

    override fun getMoviesLetters(userId: Long): List<String> = movieRepository.getMoviesLetters(userId)

    override fun getMoviesByLetter(userId: Long, letter: Char): List<MovieDTO> =
        movieRepository.getMoviesByLetter(userId, letter).map { it.toDTO() }

    override fun deleteMovie(userId: Long, kinopoiskMovieId: Long) {
        val movie = movieRepository.getMovie(userId, kinopoiskMovieId)
        movieRepository.deleteMovie(userId, kinopoiskMovieId)
        val movieLetter = movie.title.first().toUpperCase()
        if (movieRepository.getMoviesByLetter(userId, movieLetter).isEmpty()) {
            movieRepository.deleteMovieLetter(userId, movieLetter.toString())
        }
    }
}