package com.vo.movie.forecast.parser.provider.schedule.configuration

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.concurrent.ConcurrentMapCache
import org.springframework.cache.support.SimpleCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
@EnableCaching
open class ScheduleCacheConfiguration {

    companion object {
        //CACHE MANAGER
        const val SCHEDULE_CACHE_MANAGER = "scheduleCacheManager"

        //CACHE NAME
        const val MOVIES_SCHEDULE_TODAY_CACHE_NAME: String = "moviesScheduleToday"
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