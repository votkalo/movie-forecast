package com.vo.movie.forecast.parser.api.schedule

import com.vo.movie.forecast.parser.api.schedule.dto.MovieScheduleDTO

interface ScheduleApi {

    fun getMovieSchedule(alternativeLocalityName: String): List<MovieScheduleDTO>
}