package com.vo.movie.forecast.notifier.entity

import com.vo.movie.forecast.commons.data.Locality

data class LocalityAggregation(
        var locality: Locality,
        val movies: Set<MovieShort> = HashSet()
) {
    companion object {
        const val PROPERTY_LOCALITY_AGGREGATION_LOCALITY = "locality"
        const val PROPERTY_LOCALITY_AGGREGATION_MOVIES = "movies"
    }
}