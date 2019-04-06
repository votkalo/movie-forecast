package com.vo.movie.forecast.backend.storage.data

import com.vo.movie.forecast.parser.dto.locality.LocalityDTO
import com.vo.movie.forecast.parser.dto.movie.MovieDTO

data class ScheduleSearchParamsDTO(val locality: LocalityDTO,
                                   val movies: List<MovieDTO> = emptyList())