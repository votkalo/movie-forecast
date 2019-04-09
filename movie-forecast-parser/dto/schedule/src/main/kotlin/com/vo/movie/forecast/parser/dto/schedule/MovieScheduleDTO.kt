package com.vo.movie.forecast.parser.dto.schedule

import java.time.LocalDate

data class MovieScheduleDTO(val title: String,
                            val originalTitle: String?,
                            val year: String,
                            val scheduleURL: String,
                            val cinemas: List<CinemaScheduleDTO>,
                            val date: LocalDate)