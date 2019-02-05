package com.vo.movie.forecast.backend.core.entity

import com.vo.movie.forecast.commons.dto.Locality
import com.vo.movie.forecast.commons.dto.Movie
import org.springframework.data.mongodb.core.mapping.Document

const val PROPERTY_USER_USER_ID = "userId"
const val PROPERTY_USER_LOCALITY = "locality"
const val PROPERTY_USER_MOVIES = "movies"

@Document("users")
data class User(
        val userId: Long,
        val locality: Locality,
        val movies: MutableList<Movie> = ArrayList()
)