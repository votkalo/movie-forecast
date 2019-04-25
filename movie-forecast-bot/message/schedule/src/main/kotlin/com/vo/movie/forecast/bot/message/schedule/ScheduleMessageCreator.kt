package com.vo.movie.forecast.bot.message.schedule

import com.vo.movie.forecast.parser.dto.schedule.MovieScheduleDTO

interface ScheduleMessageCreator {

    fun createSchedule(localityName: String, notificationMoviesSchedule: List<MovieScheduleDTO>): String
}