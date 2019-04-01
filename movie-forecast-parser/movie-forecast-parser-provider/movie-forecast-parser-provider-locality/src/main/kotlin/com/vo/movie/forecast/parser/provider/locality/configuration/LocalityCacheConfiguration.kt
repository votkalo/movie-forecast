package com.vo.movie.forecast.parser.provider.locality.configuration

import com.vo.movie.forecast.commons.cache.ExtendableSimpleCacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.concurrent.ConcurrentMapCache
import org.springframework.context.annotation.Configuration


@Configuration
@EnableCaching
open class LocalityCacheConfiguration(extendableSimpleCacheManager: ExtendableSimpleCacheManager) {

    companion object {
        //CACHE NAME
        const val LOCALITIES_CACHE_NAME: String = "localities"
        const val LOCALITIES_LETTERS_CACHE_NAME: String = "localitiesLetters"
        const val LOCALITIES_NAMES_BY_LETTER_CACHE_NAME: String = "localitiesNamesByLetter"
        const val LOCALITY_BY_NAME_CACHE_NAME: String = "localityByName"
    }

    init {
        extendableSimpleCacheManager.registerCaches(
                arrayListOf(
                        ConcurrentMapCache(LOCALITIES_CACHE_NAME),
                        ConcurrentMapCache(LOCALITIES_LETTERS_CACHE_NAME),
                        ConcurrentMapCache(LOCALITIES_NAMES_BY_LETTER_CACHE_NAME),
                        ConcurrentMapCache(LOCALITY_BY_NAME_CACHE_NAME)
                )
        )
        println()
    }
}