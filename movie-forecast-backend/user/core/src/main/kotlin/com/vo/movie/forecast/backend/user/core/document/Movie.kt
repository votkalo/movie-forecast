package com.vo.movie.forecast.backend.user.core.document

import java.math.BigDecimal

data class Movie(
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
) {
    companion object {
        const val PROPERTY_MOVIE_TITLE = "title"
        const val PROPERTY_MOVIE_ORIGINAL_TITLE = "originalTitle"
        const val PROPERTY_MOVIE_YEAR = "year"
        const val PROPERTY_MOVIE_GENRES = "genres"
        const val PROPERTY_MOVIE_COUNTRIES = "countries"
        const val PROPERTY_MOVIE_KINOPOISK_RATING = "kinopoiskRating"
        const val PROPERTY_MOVIE_KINOPOISK_MOVIE_ID = "kinopoiskMovieId"
        const val PROPERTY_MOVIE_BIG_POSTER_URL = "bigPosterURL"
        const val PROPERTY_MOVIE_SMALL_POSTER_URL = "smallPosterURL"
        const val PROPERTY_MOVIE_SOURCE_URL = "sourceURL"
    }
}