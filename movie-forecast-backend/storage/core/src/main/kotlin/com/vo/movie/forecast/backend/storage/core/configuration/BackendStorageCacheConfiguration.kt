package com.vo.movie.forecast.backend.storage.core.configuration

import com.github.benmanes.caffeine.cache.Cache
import com.github.benmanes.caffeine.cache.Caffeine
import com.vo.movie.forecast.commons.cache.ExtendableSimpleCacheManager
import com.vo.movie.forecast.parser.dto.movie.MovieDTO
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.concurrent.ConcurrentMapCache
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.Duration


@Configuration
@EnableCaching
open class BackendStorageCacheConfiguration(cacheManager: ExtendableSimpleCacheManager,
                                            private val caffeineCacheProperties: CaffeineCacheProperties) {

    companion object {
        //CACHE NAME
        const val LOCALITIES_CACHE_NAME: String = "localities"
        const val LOCALITIES_LETTERS_CACHE_NAME: String = "localitiesLetters"
        const val LOCALITIES_NAMES_BY_LETTER_CACHE_NAME: String = "localitiesNamesByLetter"
        const val LOCALITY_BY_NAME_CACHE_NAME: String = "localityByName"
    }

    init {
        cacheManager.registerCaches(
                arrayListOf(
                        ConcurrentMapCache(LOCALITIES_CACHE_NAME),
                        ConcurrentMapCache(LOCALITIES_LETTERS_CACHE_NAME),
                        ConcurrentMapCache(LOCALITIES_NAMES_BY_LETTER_CACHE_NAME),
                        ConcurrentMapCache(LOCALITY_BY_NAME_CACHE_NAME)
                )
        )
    }

    @Bean
    open fun movieCaffeineCache(): Cache<Long, MovieDTO> {
        val movieCacheProperties = caffeineCacheProperties.movie
        return Caffeine.newBuilder()
            .expireAfterAccess(Duration.ofDays(movieCacheProperties.days))
            .maximumSize(movieCacheProperties.maxSize).build<Long, MovieDTO>()
    }
}