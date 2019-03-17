package com.vo.movie.forecast.parser.provider.schedule.configuration

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.concurrent.ConcurrentMapCache
import org.springframework.cache.support.SimpleCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary


@Configuration
@EnableCaching
open class OnlineCinemaCacheConfiguration {

    companion object {
        //CACHE MANAGER
        const val ONLINE_CINEMA_CACHE_MANAGER = "onlineCinemaCacheManager"

        //CACHE NAME
        const val ONLINE_CINEMA_MOVIE_ACCESS_INFO_CACHE_NAME: String = "OnlineCinemaMovieAccessInfo"
    }

    //TODO: Delete Primary, rewrite logic for one cache manager
    @Bean
    @Primary
    @Qualifier(ONLINE_CINEMA_CACHE_MANAGER)
    open fun onlineCinemaCacheManager(): CacheManager {
        val cacheManager = SimpleCacheManager()
        cacheManager.setCaches(
                arrayListOf(
                        ConcurrentMapCache(ONLINE_CINEMA_MOVIE_ACCESS_INFO_CACHE_NAME)
                )
        )
        return cacheManager
    }
}