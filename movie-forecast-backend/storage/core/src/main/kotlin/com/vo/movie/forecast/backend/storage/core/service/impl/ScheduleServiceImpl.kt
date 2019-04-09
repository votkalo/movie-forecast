package com.vo.movie.forecast.backend.storage.core.service.impl

import com.vo.movie.forecast.backend.storage.core.converter.toDTO
import com.vo.movie.forecast.backend.storage.core.converter.toEntity
import com.vo.movie.forecast.backend.storage.core.dao.ScheduleRepository
import com.vo.movie.forecast.backend.storage.core.service.ScheduleService
import com.vo.movie.forecast.backend.storage.core.updater.ScheduleUpdater
import com.vo.movie.forecast.backend.storage.data.ScheduleSearchParamsDTO
import com.vo.movie.forecast.parser.api.schedule.ScheduleApi
import com.vo.movie.forecast.parser.dto.movie.MovieDTO
import com.vo.movie.forecast.parser.dto.schedule.MovieScheduleDTO
import org.springframework.stereotype.Service

@Service
class ScheduleServiceImpl(private val scheduleRepository: ScheduleRepository,
                          private val scheduleApi: ScheduleApi,
                          private val scheduleUpdater: ScheduleUpdater) : ScheduleService {

    override fun getMoviesSchedule(scheduleSearchParamsDTO: ScheduleSearchParamsDTO): List<MovieScheduleDTO> {
        val locality = scheduleSearchParamsDTO.locality
        val movies = scheduleSearchParamsDTO.movies
        if (scheduleUpdater.shouldUpdate(scheduleSearchParamsDTO.locality)) {
            val moviesSchedule = scheduleApi.getMovieSchedule(locality.alternativeName)
            scheduleUpdater.update(moviesSchedule, locality)
            return getMoviesSchedule(movies, moviesSchedule)
        }
        return getMoviesSchedule(movies, scheduleRepository.getMoviesSchedule(locality.toEntity()).map { it.toDTO() })
    }

    private fun getMoviesSchedule(movies: List<MovieDTO>,
                                  moviesSchedule: List<MovieScheduleDTO>): List<MovieScheduleDTO> {
        if (movies.isEmpty()) return moviesSchedule
        return moviesSchedule.filter { it.isScheduleMatch(movies) }
    }

    private fun MovieScheduleDTO.isScheduleMatch(movies: List<MovieDTO>): Boolean =
        movies.any { it.isEqualsSchedule(this) }

    private fun MovieDTO.isEqualsSchedule(movieSchedule: MovieScheduleDTO): Boolean {
        return removeAllExceptDigitsLetters(title).equals(
                removeAllExceptDigitsLetters(movieSchedule.title),
                true
        ) && year == movieSchedule.year
    }

    private fun removeAllExceptDigitsLetters(value: String?): String? {
        return value?.replace(Regex("[\\s\\\\\\-'\"@#\$%^&*()+=<>/`~!?;:.,_]"), "")
    }
}