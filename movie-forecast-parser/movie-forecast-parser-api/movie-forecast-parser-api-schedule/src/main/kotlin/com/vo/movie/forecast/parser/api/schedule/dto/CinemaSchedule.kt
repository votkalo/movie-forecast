package com.vo.movie.forecast.parser.api.schedule.dto

data class CinemaSchedule(val name: String,
                          val scheduleURL: String,
                          val sessions: List<SessionSchedule>)