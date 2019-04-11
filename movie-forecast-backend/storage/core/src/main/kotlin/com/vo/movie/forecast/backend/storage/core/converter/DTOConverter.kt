package com.vo.movie.forecast.backend.storage.core.converter

import com.vo.movie.forecast.backend.storage.core.document.*
import com.vo.movie.forecast.parser.dto.locality.LocalityDTO
import com.vo.movie.forecast.parser.dto.movie.MovieDTO
import com.vo.movie.forecast.parser.dto.online.cinema.MovieAccessDTO
import com.vo.movie.forecast.parser.dto.online.cinema.MovieInfoDTO
import com.vo.movie.forecast.parser.dto.online.cinema.OnlineCinemaDTO
import com.vo.movie.forecast.parser.dto.schedule.CinemaScheduleDTO
import com.vo.movie.forecast.parser.dto.schedule.MovieScheduleDTO
import com.vo.movie.forecast.parser.dto.schedule.SessionScheduleDTO

// ------------ Movie ------------

fun MovieDTO.toDocument() = Movie(
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

fun LocalityDTO.toDocument() = Locality(name, alternativeName, moviesScheduleURL)

// ------------ Schedule ------------

fun MovieScheduleDTO.toDocument(locality: Locality) =
    MovieSchedule(title, originalTitle, year, scheduleURL, cinemas.map { it.toDocument() }, locality, date)

fun CinemaScheduleDTO.toDocument() = CinemaSchedule(name, scheduleURL, sessions.map { it.toDocument() })

fun SessionScheduleDTO.toDocument() = SessionSchedule(time, is3D)

// ------------ Online Cinema ------------

fun OnlineCinemaDTO.toDocument() = OnlineCinema.valueOf(name)

fun MovieInfoDTO.toDocument() = MovieInfo(title, originalTitle, year)

fun MovieAccessDTO.toDocument() = MovieAccess(title, originalTitle, year, price, currency, isAllowBySubscription, isPreOrder)
