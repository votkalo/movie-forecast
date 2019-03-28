package com.vo.movie.forecast.parser.provider.schedule.worker

import org.springframework.cache.CacheManager
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class OnlineCinemaCacheWorker(private val extendableSimpleCacheManager: CacheManager) {

    @Scheduled(cron = "0 05 00 * * ?", zone = "Europe/Minsk")
    fun cleanCaches() {
        extendableSimpleCacheManager.cacheNames.forEach { extendableSimpleCacheManager.getCache(it)?.clear() }
    }
}