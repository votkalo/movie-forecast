package com.vo.movie.forecast.parser.provider.impl

import com.github.benmanes.caffeine.cache.Cache
import com.vo.movie.forecast.parser.api.MovieApi
import com.vo.movie.forecast.commons.dto.Movie
import com.vo.movie.forecast.parser.dto.MovieSearchParams
import com.vo.movie.forecast.parser.provider.MovieProvider
import org.springframework.stereotype.Component

@Component
class MovieCacheableProvider(private val movieApi: MovieApi,
                             private val movieCaffeineCache: Cache<Long, Movie>) : MovieProvider {

    override fun searchMovie(searchParams: MovieSearchParams): List<Movie> {
        val movies = movieApi.searchMovie(searchParams)
        movies.forEach { movieCaffeineCache.put(it.kinopoiskMovieId, it) }
        return movies
    }

    override fun getMovie(kinopoiskMovieId: Long): Movie =
            movieCaffeineCache.getIfPresent(kinopoiskMovieId) ?: movieApi.getMovie(kinopoiskMovieId)
}