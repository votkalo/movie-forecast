package com.vo.movie.forecast.notifier.dao.impl

import com.vo.movie.forecast.notifier.dao.LocalityAggregationRepository
import com.vo.movie.forecast.notifier.entity.LocalityAggregation
import com.vo.movie.forecast.notifier.entity.LocalityAggregation.Companion.PROPERTY_LOCALITY_AGGREGATION_LOCALITY
import com.vo.movie.forecast.notifier.entity.LocalityAggregation.Companion.PROPERTY_LOCALITY_AGGREGATION_MOVIES
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.aggregation.Aggregation
import org.springframework.data.mongodb.core.aggregation.Fields
import org.springframework.stereotype.Repository

@Repository
open class LocalityAggregationRepositoryImpl(private val mongoOperation: MongoOperations) : LocalityAggregationRepository {

    companion object {
        private const val USERS_DOCUMENT = "users"
    }

    override fun getAllLocalityAggregations(): MutableList<LocalityAggregation> {
        val unwindStage = Aggregation.unwind(PROPERTY_LOCALITY_AGGREGATION_MOVIES)
        val groupStage = Aggregation.group(PROPERTY_LOCALITY_AGGREGATION_LOCALITY)
                .addToSet(PROPERTY_LOCALITY_AGGREGATION_MOVIES).`as`(PROPERTY_LOCALITY_AGGREGATION_MOVIES)
        val projectStage = Aggregation.project(PROPERTY_LOCALITY_AGGREGATION_MOVIES)
                .andExpression(Fields.UNDERSCORE_ID).`as`(PROPERTY_LOCALITY_AGGREGATION_LOCALITY)
        val aggregation = Aggregation.newAggregation(unwindStage, groupStage, projectStage)
        return mongoOperation.aggregate(aggregation, USERS_DOCUMENT, LocalityAggregation::class.java).mappedResults
    }
}