package com.vo.movie.forecast.parser.dto.schedule

import com.fasterxml.jackson.annotation.JsonProperty

data class SessionScheduleDTO(val time: String,
                              @get:JsonProperty("is3D") val is3D: Boolean)