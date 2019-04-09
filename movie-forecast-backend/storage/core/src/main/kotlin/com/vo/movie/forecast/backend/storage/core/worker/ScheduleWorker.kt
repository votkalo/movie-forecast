package com.vo.movie.forecast.backend.storage.core.worker

import com.vo.movie.forecast.backend.storage.core.service.LocalityService
import com.vo.movie.forecast.backend.storage.core.updater.ScheduleUpdater
import com.vo.movie.forecast.parser.api.schedule.ScheduleApi
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class ScheduleWorker(private val scheduleApi: ScheduleApi,
                     private val scheduleUpdater: ScheduleUpdater,
                     private val localityService: LocalityService) {

    @Scheduled(cron = "0 01 00 * * ?", zone = "Europe/Minsk")
    fun update() {
        localityService.getLocalities()
            .filter { scheduleUpdater.shouldUpdate(it) }
            .forEach { scheduleUpdater.update(scheduleApi.getMovieSchedule(it.alternativeName), it) }
    }

}