package com.vo.movie.forecast.backend.user.core.converter

import com.vo.movie.forecast.backend.storage.data.LocalityDTO
import com.vo.movie.forecast.backend.storage.data.MovieDTO
import com.vo.movie.forecast.backend.user.core.document.Locality
import com.vo.movie.forecast.backend.user.core.document.Movie
import com.vo.movie.forecast.backend.user.core.document.User
import com.vo.movie.forecast.backend.user.data.UserWithLocalityInfoDTO

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

// ------------ User ------------

fun User.toDTO() = UserWithLocalityInfoDTO(userId, locality!!.toDTO())