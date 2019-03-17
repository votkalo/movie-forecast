package com.vo.movie.forecast.parser.provider.movie

import com.github.benmanes.caffeine.cache.Cache
import com.vo.movie.forecast.commons.data.Movie
import com.vo.movie.forecast.parser.api.movie.MovieApi
import com.vo.movie.forecast.parser.api.movie.dto.MovieSearchParams
import com.vo.movie.forecast.parser.provider.movie.configuration.MovieCacheConfiguration
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

@Component
class MovieCacheableProvider(private val movieApi: MovieApi,
                             @Qualifier(MovieCacheConfiguration.MOVIE_CAFFEINE_CACHE) private val movieCaffeineCache: Cache<Long, Movie>) : MovieProvider {

    override fun searchMovie(searchParams: MovieSearchParams): List<Movie> {
        val movies = movieApi.searchMovie(searchParams)
        movies.forEach { movieCaffeineCache.put(it.kinopoiskMovieId, it) }
        return movies
    }

    override fun getMovie(kinopoiskMovieId: Long): Movie =
            movieCaffeineCache.getIfPresent(kinopoiskMovieId) ?: movieApi.getMovie(kinopoiskMovieId)
}