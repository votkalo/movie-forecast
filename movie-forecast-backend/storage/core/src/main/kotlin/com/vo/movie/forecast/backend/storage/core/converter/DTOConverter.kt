package com.vo.movie.forecast.backend.user.core.converter

import com.vo.movie.forecast.backend.storage.core.document.Locality
import com.vo.movie.forecast.backend.storage.core.document.Movie
import com.vo.movie.forecast.backend.storage.data.LocalityDTO
import com.vo.movie.forecast.backend.storage.data.MovieDTO

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
