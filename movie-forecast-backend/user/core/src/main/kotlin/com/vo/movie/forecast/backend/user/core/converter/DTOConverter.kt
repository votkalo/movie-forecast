package com.vo.movie.forecast.backend.user.core.converter

import com.vo.movie.forecast.backend.storage.data.LocalityDTO
import com.vo.movie.forecast.backend.storage.data.MovieDTO
import com.vo.movie.forecast.backend.user.core.document.Locality
import com.vo.movie.forecast.backend.user.core.document.Movie
import com.vo.movie.forecast.backend.user.core.document.MovieFilter
import com.vo.movie.forecast.backend.user.data.MovieFilterDTO

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


fun MovieFilterDTO.toEntity() = MovieFilter(title, originalTitle, year)

// ------------ Locality ------------

fun LocalityDTO.toEntity() = Locality(name, alternativeName, moviesScheduleURL)
