package com.vo.movie.forecast.parser.feign

import com.vo.movie.forecast.parser.api.ScheduleApi
import com.vo.movie.forecast.parser.dto.MovieSchedule
import feign.Param
import feign.RequestLine

interface ScheduleApiFeign : ScheduleApi {

    @RequestLine("GET /schedule/movies/{alternativeLocalityName}/today")
    override fun getMovieSchedule(@Param("alternativeLocalityName") alternativeLocalityName: String): List<MovieSchedule>
}