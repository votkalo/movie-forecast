package com.vo.movie.forecast.notifier.dao

import com.vo.movie.forecast.notifier.entity.LocalityAggregation

interface LocalityAggregationRepository {

    fun getAllLocalityAggregations(): MutableList<LocalityAggregation>
}