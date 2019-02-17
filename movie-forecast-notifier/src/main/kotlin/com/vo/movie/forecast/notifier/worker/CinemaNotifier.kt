package com.vo.movie.forecast.notifier.worker

import com.vo.movie.forecast.backend.api.notifier.UserApi
import com.vo.movie.forecast.parser.provider.schedule.ScheduleProvider
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class CinemaNotifier(private val userApi: UserApi,
                     private val scheduleProvider: ScheduleProvider) {

    @Scheduled(cron = "0 01 00 * * ?", zone = "Europe/Minsk")
    fun notifyUsersAboutMoviesInCinema() {

    }
}