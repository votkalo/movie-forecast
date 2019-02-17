package com.vo.movie.forecast.backend.data

import com.vo.movie.forecast.commons.data.Locality
import com.vo.movie.forecast.commons.data.Movie

data class User(
        val userId: Long,
        val locality: Locality,
        val movies: MutableList<Movie> = ArrayList()
) {
    companion object {
        const val PROPERTY_USER_USER_ID = "userId"
        const val PROPERTY_USER_LOCALITY = "locality"
        const val PROPERTY_USER_MOVIES = "movies"
    }
}
