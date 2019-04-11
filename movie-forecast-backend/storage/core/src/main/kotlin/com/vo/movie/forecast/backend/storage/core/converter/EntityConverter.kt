package com.vo.movie.forecast.backend.storage.core.converter

import com.vo.movie.forecast.backend.storage.core.document.*
import com.vo.movie.forecast.parser.dto.locality.LocalityDTO
import com.vo.movie.forecast.parser.dto.movie.MovieDTO
import com.vo.movie.forecast.parser.dto.online.cinema.MovieAccessDTO
import com.vo.movie.forecast.parser.dto.schedule.CinemaScheduleDTO
import com.vo.movie.forecast.parser.dto.schedule.MovieScheduleDTO
import com.vo.movie.forecast.parser.dto.schedule.SessionScheduleDTO

// ------------ Movie ------------

fun Movie.toDTO() = MovieDTO(
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

fun Locality.toDTO() = LocalityDTO(name, alternativeName, moviesScheduleURL)

// ------------ Schedule ------------

fun MovieSchedule.toDTO() = MovieScheduleDTO(title, originalTitle, year, scheduleURL, cinemas.map { it.toDTO() }, date)

fun CinemaSchedule.toDTO() = CinemaScheduleDTO(name, scheduleURL, sessions.map { it.toDTO() })

fun SessionSchedule.toDTO() = SessionScheduleDTO(time, is3D)

// ------------ Online Cinema ------------

fun MovieAccess.toDTO() = MovieAccessDTO(title, originalTitle, year, price, currency, isAllowBySubscription, isPreOrder)
