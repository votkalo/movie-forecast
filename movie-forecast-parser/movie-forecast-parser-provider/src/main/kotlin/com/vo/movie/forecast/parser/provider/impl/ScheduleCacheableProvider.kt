package com.vo.movie.forecast.parser.provider.impl

import com.vo.movie.forecast.parser.api.ScheduleApi
import com.vo.movie.forecast.parser.dto.MovieSchedule
import com.vo.movie.forecast.parser.provider.ScheduleProvider
import com.vo.movie.forecast.parser.provider.configuration.CacheConfiguration.Companion.MOVIES_SCHEDULE_TODAY_CACHE_NAME
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Component

@Component
open class ScheduleCacheableProvider(private val scheduleApi: ScheduleApi) : ScheduleProvider {

    @Cacheable(cacheNames = [MOVIES_SCHEDULE_TODAY_CACHE_NAME])
    override fun getMovieSchedule(alternativeLocalityName: String): List<MovieSchedule> = scheduleApi.getMovieSchedule(alternativeLocalityName)
}