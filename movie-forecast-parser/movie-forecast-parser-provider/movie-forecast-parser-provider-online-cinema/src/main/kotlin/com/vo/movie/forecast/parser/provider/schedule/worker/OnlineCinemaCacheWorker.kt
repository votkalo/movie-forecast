package com.vo.movie.forecast.parser.provider.schedule.worker

import com.vo.movie.forecast.commons.cache.ExtendableSimpleCacheManager
import com.vo.movie.forecast.parser.provider.schedule.configuration.OnlineCinemaCacheConfiguration.Companion.ONLINE_CINEMA_MOVIE_ACCESS_INFO_CACHE_NAME
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class OnlineCinemaCacheWorker(private val extendableSimpleCacheManager: ExtendableSimpleCacheManager) {

    @Scheduled(cron = "0 05 00 * * ?", zone = "Europe/Minsk")
    fun cleanCaches() {
        extendableSimpleCacheManager.clearCaches(ONLINE_CINEMA_MOVIE_ACCESS_INFO_CACHE_NAME)
    }
}