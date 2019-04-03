package com.vo.movie.forecast.parser.provider.movie

import com.github.benmanes.caffeine.cache.Cache
import com.vo.movie.forecast.backend.storage.data.MovieDTO
import com.vo.movie.forecast.parser.api.movie.MovieApi
import com.vo.movie.forecast.parser.api.movie.dto.MovieSearchParamsDTO
import org.springframework.stereotype.Component

@Component
class MovieCacheableProvider(private val movieApi: MovieApi,
                             private val movieCaffeineCache: Cache<Long, MovieDTO>
) : MovieProvider {

    override fun searchMovie(searchParams: MovieSearchParamsDTO): List<MovieDTO> {
        val movies = movieApi.searchMovie(searchParams)
        movies.forEach { movieCaffeineCache.put(it.kinopoiskMovieId, it) }
        return movies
    }

    override fun getMovie(kinopoiskMovieId: Long): MovieDTO =
            movieCaffeineCache.getIfPresent(kinopoiskMovieId) ?: movieApi.getMovie(kinopoiskMovieId)
}