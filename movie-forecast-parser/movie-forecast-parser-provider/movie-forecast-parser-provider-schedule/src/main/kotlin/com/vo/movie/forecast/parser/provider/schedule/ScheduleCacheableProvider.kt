package com.vo.movie.forecast.parser.provider.schedule

import com.vo.movie.forecast.parser.api.schedule.ScheduleApi
import com.vo.movie.forecast.parser.api.schedule.dto.MovieSchedule
import com.vo.movie.forecast.parser.provider.schedule.configuration.ScheduleCacheConfiguration.Companion.MOVIES_SCHEDULE_TODAY_CACHE_NAME
import com.vo.movie.forecast.parser.provider.schedule.configuration.ScheduleCacheConfiguration.Companion.SCHEDULE_CACHE_MANAGER
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Component

@Component
open class ScheduleCacheableProvider(private val scheduleApi: ScheduleApi) : ScheduleProvider {

    @Cacheable(cacheNames = [MOVIES_SCHEDULE_TODAY_CACHE_NAME], cacheManager = SCHEDULE_CACHE_MANAGER)
    override fun getMovieSchedule(alternativeLocalityName: String): List<MovieSchedule> = scheduleApi.getMovieSchedule(alternativeLocalityName)
}