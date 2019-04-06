package com.vo.movie.forecast.backend.storage.core.converter

import com.vo.movie.forecast.backend.storage.core.document.CinemaSchedule
import com.vo.movie.forecast.backend.storage.core.document.Locality
import com.vo.movie.forecast.backend.storage.core.document.Movie
import com.vo.movie.forecast.backend.storage.core.document.MovieSchedule
import com.vo.movie.forecast.parser.dto.locality.LocalityDTO
import com.vo.movie.forecast.parser.dto.movie.MovieDTO
import com.vo.movie.forecast.parser.dto.schedule.CinemaScheduleDTO
import com.vo.movie.forecast.parser.dto.schedule.MovieScheduleDTO
import com.vo.movie.forecast.backend.storage.core.document.SessionSchedule
import com.vo.movie.forecast.parser.dto.schedule.SessionScheduleDTO

// ------------ Movie ------------

fun MovieDTO.toEntity() = Movie(
        title,
        originalTitle,
        year,
        genres,
        countries,
        kinopoiskRating,
        kinopoiskMovieId,
        bigPosterURL,
        smallPosterURL,
        sourceURL
)

// ------------ Locality ------------

fun LocalityDTO.toEntity() = Locality(name, alternativeName, moviesScheduleURL)

// ------------ Schedule ------------

fun MovieScheduleDTO.toEntity(locality: Locality) =
    MovieSchedule(title, originalTitle, year, scheduleURL, cinemas.map { it.toEntity() }, locality)

fun CinemaScheduleDTO.toEntity() = CinemaSchedule(name, scheduleURL, sessions.map { it.toEntity() })

fun SessionScheduleDTO.toEntity() = SessionSchedule(time, is3D)

