package com.vo.movie.forecast.backend.storage.data

import java.math.BigDecimal

data class MovieDTO(
    val title: String,
    val originalTitle: String?,
    val year: String?,
    val genres: String?,
    val countries: String?,
    val kinopoiskRating: BigDecimal?,
    val kinopoiskMovieId: Long,
    val bigPosterURL: String,
    val smallPosterURL: String,
    val sourceURL: String
)