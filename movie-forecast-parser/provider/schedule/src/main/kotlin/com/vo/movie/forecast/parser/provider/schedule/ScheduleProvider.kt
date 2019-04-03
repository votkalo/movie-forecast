package com.vo.movie.forecast.parser.provider.schedule

import com.vo.movie.forecast.parser.api.schedule.dto.MovieScheduleDTO

interface ScheduleProvider {

    fun getMovieSchedule(alternativeLocalityName: String): List<MovieScheduleDTO>
}