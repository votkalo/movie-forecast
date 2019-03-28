package com.vo.movie.forecast.parser.provider.schedule.configuration

import com.vo.movie.forecast.commons.cache.ExtendableSimpleCacheManager
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.concurrent.ConcurrentMapCache
import org.springframework.context.annotation.Configuration


@Configuration
@EnableCaching
open class ScheduleCacheConfiguration(extendableSimpleCacheManager: CacheManager) {

    companion object {
        //CACHE NAME
        const val MOVIES_SCHEDULE_TODAY_CACHE_NAME: String = "moviesScheduleToday"
    }

    init {
        if (extendableSimpleCacheManager !is ExtendableSimpleCacheManager) {
            throw IllegalArgumentException("CacheManager must be ${ExtendableSimpleCacheManager::class.java.name} type")
        }
        extendableSimpleCacheManager.registerCache(ConcurrentMapCache(MOVIES_SCHEDULE_TODAY_CACHE_NAME))
    }
}