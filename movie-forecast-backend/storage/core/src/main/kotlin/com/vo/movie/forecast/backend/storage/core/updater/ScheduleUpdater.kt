package com.vo.movie.forecast.backend.storage.core.updater

import com.vo.movie.forecast.backend.storage.core.converter.toDocument
import com.vo.movie.forecast.backend.storage.core.dao.ScheduleRepository
import com.vo.movie.forecast.parser.dto.locality.LocalityDTO
import com.vo.movie.forecast.parser.dto.schedule.MovieScheduleDTO
import org.springframework.stereotype.Component

@Component
class ScheduleUpdater(private val scheduleRepository: ScheduleRepository) {

    fun update(items: List<MovieScheduleDTO>, locality: LocalityDTO) {
        scheduleRepository.clearNotTodaySchedule()
        if (!shouldUpdate(locality)) return
        items.forEach { scheduleRepository.save(it.toDocument(locality.toDocument())) }
    }

    fun shouldUpdate(locality: LocalityDTO): Boolean = !scheduleRepository.existsTodaySchedule(locality.toDocument())
}