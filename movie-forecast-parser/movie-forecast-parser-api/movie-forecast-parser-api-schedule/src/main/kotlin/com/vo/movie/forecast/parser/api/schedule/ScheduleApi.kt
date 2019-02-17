package com.vo.movie.forecast.parser.api.schedule

import com.vo.movie.forecast.parser.api.schedule.dto.MovieSchedule

interface ScheduleApi {

    fun getMovieSchedule(alternativeLocalityName: String): List<MovieSchedule>
}