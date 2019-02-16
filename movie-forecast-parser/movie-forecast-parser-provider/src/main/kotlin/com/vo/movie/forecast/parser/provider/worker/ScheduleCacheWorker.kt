package com.vo.movie.forecast.parser.provider.worker

import com.vo.movie.forecast.parser.provider.configuration.CacheConfiguration.Companion.SCHEDULE_CACHE_MANAGER
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.cache.CacheManager
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class ScheduleCacheWorker(@Qualifier(SCHEDULE_CACHE_MANAGER) private val cacheManager: CacheManager) {

    @Scheduled(cron = "0 01 00 * * ?", zone = "Europe/Minsk")
    fun cleanCaches() {
        cacheManager.cacheNames.forEach { cacheManager.getCache(it)?.clear() }
    }
}