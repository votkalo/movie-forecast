package com.vo.movie.forecast.backend.storage.core.service

import com.vo.movie.forecast.backend.storage.data.ScheduleSearchParamsDTO
import com.vo.movie.forecast.parser.dto.schedule.MovieScheduleDTO

interface ScheduleService {

    fun getMoviesSchedule(scheduleSearchParamsDTO: ScheduleSearchParamsDTO): List<MovieScheduleDTO>
}