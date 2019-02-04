package com.vo.movie.forecast.parser.provider.configuration

import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.concurrent.ConcurrentMapCache
import org.springframework.cache.support.SimpleCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
@EnableCaching
open class MovieForecastParserCacheConfiguration {

    companion object {
        const val LOCALITIES_CACHE_NAME: String = "localities"
        const val LOCALITIES_LETTERS_CACHE_NAME: String = "localitiesLetters"
        const val LOCALITIES_NAMES_BY_LETTER_CACHE_NAME: String = "localitiesNamesByLetter"
        const val LOCALITY_BY_NAME_CACHE_NAME: String = "localityByName"
    }

    @Bean
    open fun cacheManager(): CacheManager {
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
}