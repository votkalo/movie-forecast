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
                 val sourceURL: String) {
    companion object {
        const val PROPERTY_MOVIE_TITLE = "title"
        const val PROPERTY_MOVIE_ORIGINAL_TITLE = "originalTitle"
        const val PROPERTY_MOVIE_YEAR = "year"
        const val PROPERTY_MOVIE_KINOPOISK_MOVIE_ID = "kinopoiskMovieId"
    }
}