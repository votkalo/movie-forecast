package com.vo.movie.forecast.backend.storage.feign

import com.vo.movie.forecast.backend.storage.api.ScheduleApi
import com.vo.movie.forecast.backend.storage.data.ScheduleSearchParamsDTO
import com.vo.movie.forecast.parser.dto.schedule.MovieScheduleDTO
import feign.RequestLine

interface ScheduleApiFeign : ScheduleApi {

    @RequestLine("POST /schedule/movies/today")
    override fun getMoviesSchedule(scheduleSearchParamsDTO: ScheduleSearchParamsDTO): List<MovieScheduleDTO>
}