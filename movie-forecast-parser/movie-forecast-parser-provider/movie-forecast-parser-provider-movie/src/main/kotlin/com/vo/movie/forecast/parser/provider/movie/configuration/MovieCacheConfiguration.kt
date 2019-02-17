package com.vo.movie.forecast.parser.provider.movie.configuration

import com.github.benmanes.caffeine.cache.Cache
import com.github.benmanes.caffeine.cache.Caffeine
import com.vo.movie.forecast.commons.data.Movie
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.Duration


@Configuration
@EnableCaching
open class MovieCacheConfiguration(private val caffeineCacheProperties: CaffeineCacheProperties) {

    companion object {
        //CACHE CAFFEINE
        const val MOVIE_CAFFEINE_CACHE = "movieCaffeineCache"
    }

    @Bean
    @Qualifier(MOVIE_CAFFEINE_CACHE)
    open fun movieCaffeineCache(): Cache<Long, Movie> {
        val movieCacheProperties = caffeineCacheProperties.movie
        return Caffeine.newBuilder()
                .expireAfterAccess(Duration.ofDays(movieCacheProperties.days))
                .maximumSize(movieCacheProperties.maxSize)
                .build<Long, Movie>()
    }
}