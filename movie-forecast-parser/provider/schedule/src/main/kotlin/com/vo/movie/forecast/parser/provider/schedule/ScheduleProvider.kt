package com.vo.movie.forecast.parser.provider.schedule

import com.vo.movie.forecast.parser.api.schedule.dto.MovieSchedule

interface ScheduleProvider {

    fun getMovieSchedule(alternativeLocalityName: String): List<MovieSchedule>
}