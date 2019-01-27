package com.vo.movie.forecast.parser.provider.worker

import org.springframework.cache.CacheManager
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component


@Component
class MovieForecastParserCacheEvictionWorker(private val cacheManager: CacheManager) {

    @Scheduled(cron = "0 0 0 1 * ?")
    fun evictAllCachesAtIntervals() {
        cacheManager.cacheNames.forEach { cacheManager.getCache(it)?.clear() }
    }
}