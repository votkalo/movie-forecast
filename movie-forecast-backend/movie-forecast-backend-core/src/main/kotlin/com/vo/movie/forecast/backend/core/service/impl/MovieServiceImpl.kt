package com.vo.movie.forecast.backend.core.service.impl

import com.vo.movie.forecast.backend.core.dao.MovieRepository
import com.vo.movie.forecast.backend.core.service.MovieService
import com.vo.movie.forecast.backend.data.MovieInfo
import org.springframework.stereotype.Service

@Service
class MovieServiceImpl(private val movieRepository: MovieRepository) : MovieService {

    override fun getUserMovies(userId: Long, page: Int, size: Int): List<MovieInfo> = movieRepository.getUserMovies(userId, page, size)
}