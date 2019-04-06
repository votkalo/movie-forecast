package com.vo.movie.forecast.backend.storage.core.rest

import com.vo.movie.forecast.backend.storage.core.service.ScheduleService
import com.vo.movie.forecast.backend.storage.data.ScheduleSearchParamsDTO
import com.vo.movie.forecast.parser.dto.schedule.MovieScheduleDTO
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/schedule")
class ScheduleController(private val scheduleService: ScheduleService) {

    @PostMapping("/movies/today")
    fun getMoviesSchedule(@RequestBody scheduleSearchParamsDTO: ScheduleSearchParamsDTO): List<MovieScheduleDTO> =
        scheduleService.getMoviesSchedule(scheduleSearchParamsDTO)

}