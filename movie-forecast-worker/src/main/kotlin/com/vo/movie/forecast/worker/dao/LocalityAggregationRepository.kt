package com.vo.movie.forecast.worker.dao

import com.vo.movie.forecast.worker.entity.LocalityAggregation

interface LocalityAggregationRepository {

    fun getAllLocalityAggregations(): MutableList<LocalityAggregation>
}