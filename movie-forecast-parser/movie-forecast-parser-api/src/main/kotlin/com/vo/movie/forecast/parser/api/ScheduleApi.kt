package com.vo.movie.forecast.parser.api

import com.vo.movie.forecast.parser.dto.MovieSchedule

interface ScheduleApi {

    fun getMovieSchedule(alternativeLocalityName: String): List<MovieSchedule>
}