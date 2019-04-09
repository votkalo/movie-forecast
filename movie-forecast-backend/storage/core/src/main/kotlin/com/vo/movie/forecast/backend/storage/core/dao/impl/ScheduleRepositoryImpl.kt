package com.vo.movie.forecast.backend.storage.core.dao.impl

import com.vo.movie.forecast.backend.storage.core.dao.ScheduleRepository
import com.vo.movie.forecast.backend.storage.core.document.Locality
import com.vo.movie.forecast.backend.storage.core.document.Locality.Companion.PROPERTY_LOCALITY_NAME
import com.vo.movie.forecast.backend.storage.core.document.MovieSchedule
import com.vo.movie.forecast.backend.storage.core.document.MovieSchedule.Companion.PROPERTY_SCHEDULE_DATE
import com.vo.movie.forecast.backend.storage.core.document.MovieSchedule.Companion.PROPERTY_SCHEDULE_LOCALITY
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
open class ScheduleRepositoryImpl(private val mongoOperation: MongoOperations) : ScheduleRepository {

    override fun getMoviesSchedule(locality: Locality): List<MovieSchedule> {
        val query = Query(Criteria.where("$PROPERTY_SCHEDULE_LOCALITY.$PROPERTY_LOCALITY_NAME").`is`(locality.name))
        return mongoOperation.find(query, MovieSchedule::class.java)
    }

    override fun save(movieSchedule: MovieSchedule) {
        mongoOperation.save(movieSchedule)
    }

    override fun clearNotTodaySchedule() {
        mongoOperation.remove(
                Query(Criteria.where(PROPERTY_SCHEDULE_DATE).ne(LocalDate.now())),
                MovieSchedule::class.java
        )
    }

    override fun existsTodaySchedule(locality: Locality): Boolean {
        val query = Query(Criteria.where(PROPERTY_SCHEDULE_DATE).`is`(LocalDate.now()))
            .addCriteria(Criteria.where("$PROPERTY_SCHEDULE_LOCALITY.$PROPERTY_LOCALITY_NAME").`is`(locality.name))
        return mongoOperation.exists(query, MovieSchedule::class.java)
    }
}