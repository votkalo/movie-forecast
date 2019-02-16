package com.vo.movie.forecast.parser.provider

import com.vo.movie.forecast.parser.dto.MovieSchedule

interface ScheduleProvider {

    fun getMovieSchedule(alternativeLocalityName: String): List<MovieSchedule>
}