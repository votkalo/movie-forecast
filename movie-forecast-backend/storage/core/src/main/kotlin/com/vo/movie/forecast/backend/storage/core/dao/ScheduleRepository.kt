package com.vo.movie.forecast.backend.storage.core.dao

import com.vo.movie.forecast.backend.storage.core.document.Locality
import com.vo.movie.forecast.backend.storage.core.document.MovieSchedule

interface ScheduleRepository {

    fun existsSchedule(): Boolean

    fun getMoviesSchedule(locality: Locality): List<MovieSchedule>

    fun save(movieSchedule: MovieSchedule)
}