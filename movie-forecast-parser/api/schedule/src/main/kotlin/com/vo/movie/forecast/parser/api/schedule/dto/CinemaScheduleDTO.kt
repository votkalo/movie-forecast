package com.vo.movie.forecast.parser.api.schedule.dto

data class CinemaScheduleDTO(val name: String, val scheduleURL: String, val sessions: List<SessionScheduleDTO>)