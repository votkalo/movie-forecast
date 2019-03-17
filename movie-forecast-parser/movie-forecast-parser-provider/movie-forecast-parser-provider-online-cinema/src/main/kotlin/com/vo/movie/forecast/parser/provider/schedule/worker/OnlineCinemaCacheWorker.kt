package com.vo.movie.forecast.parser.provider.schedule.worker

import com.vo.movie.forecast.parser.provider.schedule.configuration.OnlineCinemaCacheConfiguration.Companion.ONLINE_CINEMA_CACHE_MANAGER
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.cache.CacheManager
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class OnlineCinemaCacheWorker(@Qualifier(ONLINE_CINEMA_CACHE_MANAGER) private val cacheManager: CacheManager) {

    @Scheduled(cron = "0 05 00 * * ?", zone = "Europe/Minsk")
    fun cleanCaches() {
        cacheManager.cacheNames.forEach { cacheManager.getCache(it)?.clear() }
    }
}