package com.vo.movie.forecast.parser.provider.schedule.configuration

import com.vo.movie.forecast.commons.cache.ExtendableSimpleCacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.concurrent.ConcurrentMapCache
import org.springframework.context.annotation.Configuration


@Configuration
@EnableCaching
open class OnlineCinemaCacheConfiguration(extendableSimpleCacheManager: ExtendableSimpleCacheManager) {

    companion object {
        //CACHE NAME
        const val ONLINE_CINEMA_MOVIE_ACCESS_INFO_CACHE_NAME: String = "OnlineCinemaMovieAccessInfo"
    }

    init {
        extendableSimpleCacheManager.registerCache(ConcurrentMapCache(ONLINE_CINEMA_MOVIE_ACCESS_INFO_CACHE_NAME))
    }
}