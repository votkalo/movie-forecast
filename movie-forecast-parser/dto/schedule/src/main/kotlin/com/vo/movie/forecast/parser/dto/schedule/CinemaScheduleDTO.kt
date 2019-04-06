package com.vo.movie.forecast.parser.dto.schedule

data class CinemaScheduleDTO(val name: String, val scheduleURL: String, val sessions: List<SessionScheduleDTO>)