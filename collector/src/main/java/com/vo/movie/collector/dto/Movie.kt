package com.vo.movie.collector.dto

import java.math.BigDecimal

data class Movie(val year: Int,
                 val russianTitle: String,
                 val alternativeTitle: String,
                 val sourceURL: String,
                 val russianPremiere: String,
                 val worldPremiere: String? = null,
                 val tagline: String? = null,
                 val bluRayRelease: String? = null,
                 val digitalRelease: String? = null,
                 val reRelease: String? = null,
                 val allowAge: String? = null,
                 val pg: String? = null,
                 val duration: String? = null,
                 val kinopoiskRating: BigDecimal? = null,
                 val imdbRating: BigDecimal? = null,
                 val counties: List<String> = ArrayList(),
                 val genres: List<String> = ArrayList(),
                 var posterURL: String? = null)