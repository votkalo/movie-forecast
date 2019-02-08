package com.vo.movie.forecast.commons.data

import java.math.BigDecimal

data class Movie(val title: String,
                 val originalTitle: String?,
                 val year: String?,
                 val genres: String?,
                 val countries: String?,
                 val kinopoiskRating: BigDecimal?,
                 val kinopoiskMovieId: Long,
                 val bigPosterURL: String,
                 val smallPosterURL: String,
                 val sourceURL: String)