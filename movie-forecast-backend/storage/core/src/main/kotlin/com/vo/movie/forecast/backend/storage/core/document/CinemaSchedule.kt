package com.vo.movie.forecast.backend.storage.core.document

data class CinemaSchedule(val name: String,
                          val scheduleURL: String,
                          val sessions: List<SessionSchedule>)