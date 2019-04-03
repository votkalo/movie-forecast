package com.vo.movie.forecast.parser.feign.schedule

import com.vo.movie.forecast.parser.api.schedule.ScheduleApi
import com.vo.movie.forecast.parser.api.schedule.dto.MovieScheduleDTO
import feign.Param
import feign.RequestLine

interface ScheduleApiFeign : ScheduleApi {

    @RequestLine("GET /schedule/movies/{alternativeLocalityName}/today")
    override fun getMovieSchedule(@Param("alternativeLocalityName") alternativeLocalityName: String): List<MovieScheduleDTO>
}