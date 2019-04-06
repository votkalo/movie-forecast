package com.vo.movie.forecast.backend.storage.api

import com.vo.movie.forecast.backend.storage.data.ScheduleSearchParamsDTO
import com.vo.movie.forecast.parser.dto.schedule.MovieScheduleDTO

interface ScheduleApi {

    fun getMoviesSchedule(scheduleSearchParamsDTO: ScheduleSearchParamsDTO): List<MovieScheduleDTO>
}