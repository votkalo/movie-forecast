package com.vo.movie.forecast.backend.storage.core.service.impl

import com.github.benmanes.caffeine.cache.Cache
import com.vo.movie.forecast.backend.storage.core.converter.toDTO
import com.vo.movie.forecast.backend.storage.core.converter.toDocument
import com.vo.movie.forecast.backend.storage.core.dao.MovieRepository
import com.vo.movie.forecast.backend.storage.core.service.MovieService
import com.vo.movie.forecast.parser.api.movie.MovieApi
import com.vo.movie.forecast.parser.dto.movie.MovieDTO
import com.vo.movie.forecast.parser.dto.movie.MovieSearchParamsDTO
import org.springframework.stereotype.Service

@Service
class MovieServiceImpl(private val movieRepository: MovieRepository,
                       private val movieApi: MovieApi,
                       private val movieCaffeineCache: Cache<Long, MovieDTO>) : MovieService {

    override fun searchMovie(searchParams: MovieSearchParamsDTO): List<MovieDTO> {
        val movies = movieApi.searchMovie(searchParams)
        movies.forEach {
            movieRepository.saveMovie(it.toDocument())
            movieCaffeineCache.put(it.kinopoiskMovieId, it)
        }
        return movies
    }

    override fun getMovie(kinopoiskMovieId: Long): MovieDTO =
        movieCaffeineCache.getIfPresent(kinopoiskMovieId)
            ?: movieRepository.getMovie(kinopoiskMovieId)?.toDTO()
            ?: movieApi.getMovie(kinopoiskMovieId)

}