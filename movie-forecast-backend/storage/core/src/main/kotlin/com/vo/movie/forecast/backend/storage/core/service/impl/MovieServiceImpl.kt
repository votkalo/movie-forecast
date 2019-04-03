package com.vo.movie.forecast.backend.storage.core.service.impl

import com.vo.movie.forecast.backend.storage.core.dao.MovieRepository
import com.vo.movie.forecast.backend.storage.core.service.MovieService
import com.vo.movie.forecast.backend.storage.data.MovieDTO
import com.vo.movie.forecast.backend.storage.data.MovieSearchParamsDTO
import com.vo.movie.forecast.backend.user.core.converter.toEntity
import com.vo.movie.forecast.parser.provider.movie.MovieProvider
import org.springframework.stereotype.Service

@Service
class MovieServiceImpl(private val movieRepository: MovieRepository,
                       private val movieProvider: MovieProvider) : MovieService {

    override fun searchMovie(searchParams: MovieSearchParamsDTO): List<MovieDTO> {
        val movies = movieProvider.searchMovie(searchParams)
        movies.forEach { movieRepository.saveMovie(it.toEntity()) }
        return movies
    }

}