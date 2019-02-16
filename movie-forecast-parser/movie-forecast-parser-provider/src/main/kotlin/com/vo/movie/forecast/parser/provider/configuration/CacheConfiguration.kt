package com.vo.movie.forecast.parser.provider.configuration

import com.github.benmanes.caffeine.cache.Cache
import com.github.benmanes.caffeine.cache.Caffeine
import com.vo.movie.forecast.commons.data.Movie
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.concurrent.ConcurrentMapCache
import org.springframework.cache.support.SimpleCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.Duration


@Configuration
@EnableCaching
open class CacheConfiguration(private val caffeineCacheProperties: CaffeineCacheProperties) {

    companion object {
        //CACHES
        const val LOCALITY_CACHE_MANAGER = "localityCacheManager"
        const val MOVIE_CAFFEINE_CACHE = "movieCaffeineCache"
        const val SCHEDULE_CACHE_MANAGER = "scheduleCacheManager"

        //LOCALITY
        const val LOCALITIES_CACHE_NAME: String = "localities"
        const val LOCALITIES_LETTERS_CACHE_NAME: String = "localitiesLetters"
        const val LOCALITIES_NAMES_BY_LETTER_CACHE_NAME: String = "localitiesNamesByLetter"
        const val LOCALITY_BY_NAME_CACHE_NAME: String = "localityByName"

        //SCHEDULE
        const val MOVIES_SCHEDULE_TODAY_CACHE_NAME: String = "moviesScheduleToday"
    }

    @Bean
    @Qualifier(LOCALITY_CACHE_MANAGER)
    open fun localityCacheManager(): CacheManager {
        val cacheManager = SimpleCacheManager()
        cacheManager.setCaches(
                arrayListOf(
                        ConcurrentMapCache(LOCALITIES_CACHE_NAME),
                        ConcurrentMapCache(LOCALITIES_LETTERS_CACHE_NAME),
                        ConcurrentMapCache(LOCALITIES_NAMES_BY_LETTER_CACHE_NAME),
                        ConcurrentMapCache(LOCALITY_BY_NAME_CACHE_NAME)
                )
        )
        return cacheManager
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

    @Bean
    @Qualifier(SCHEDULE_CACHE_MANAGER)
    open fun scheduleCacheManager(): CacheManager {
        val cacheManager = SimpleCacheManager()
        cacheManager.setCaches(
                arrayListOf(
                        ConcurrentMapCache(MOVIES_SCHEDULE_TODAY_CACHE_NAME)
                )
        )
        return cacheManager
    }
}