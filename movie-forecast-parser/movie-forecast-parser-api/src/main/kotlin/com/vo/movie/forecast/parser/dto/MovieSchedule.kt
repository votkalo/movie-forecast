package com.vo.movie.forecast.parser.dto

data class MovieSchedule(val title: String,
                         val scheduleURL: String,
                         val cinemas: List<CinemaSchedule>)