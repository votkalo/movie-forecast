package com.vo.movie.forecast.backend.storage.core.dao.impl

import com.vo.movie.forecast.backend.storage.core.dao.ScheduleRepository
import com.vo.movie.forecast.backend.storage.core.document.Locality
import com.vo.movie.forecast.backend.storage.core.document.Locality.Companion.PROPERTY_LOCALITY_NAME
import com.vo.movie.forecast.backend.storage.core.document.MovieSchedule
import com.vo.movie.forecast.backend.storage.core.document.MovieSchedule.Companion.PROPERTY_SCHEDULE_LOCALITY
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Repository

@Repository
open class ScheduleRepositoryImpl(private val mongoOperation: MongoOperations) : ScheduleRepository {

    override fun existsSchedule(): Boolean = mongoOperation.exists(Query(), MovieSchedule::class.java)

    override fun getMoviesSchedule(locality: Locality): List<MovieSchedule> {
        val query = Query(Criteria.where("$PROPERTY_SCHEDULE_LOCALITY.$PROPERTY_LOCALITY_NAME").`is`(locality.name))
        return mongoOperation.find(query, MovieSchedule::class.java)
    }

    override fun save(movieSchedule: MovieSchedule) {
        mongoOperation.save(movieSchedule)
    }
}