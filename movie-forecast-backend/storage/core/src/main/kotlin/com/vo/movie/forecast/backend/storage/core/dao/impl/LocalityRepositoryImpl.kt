package com.vo.movie.forecast.backend.storage.core.dao.impl

import com.vo.movie.forecast.backend.storage.core.dao.LocalityRepository
import com.vo.movie.forecast.backend.storage.core.document.Locality
import com.vo.movie.forecast.backend.storage.core.document.Locality.Companion.PROPERTY_LOCALITY_ALTERNATIVE_NAME
import com.vo.movie.forecast.backend.storage.core.document.Locality.Companion.PROPERTY_LOCALITY_MOVIES_SCHEDULE_URL
import com.vo.movie.forecast.backend.storage.core.document.Locality.Companion.PROPERTY_LOCALITY_NAME
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.stereotype.Repository
import java.util.regex.Pattern

@Repository
open class LocalityRepositoryImpl(private val mongoOperation: MongoOperations) : LocalityRepository {

    override fun getLocalities(): List<Locality> = mongoOperation.findAll(Locality::class.java)

    override fun saveLocality(locality: Locality) {
        val update = Update()
        update.set(PROPERTY_LOCALITY_ALTERNATIVE_NAME, locality.alternativeName)
        update.set(PROPERTY_LOCALITY_MOVIES_SCHEDULE_URL, locality.moviesScheduleURL)
        mongoOperation.upsert(queryLocalityName(locality.name), update, Locality::class.java)
    }

    override fun getLocalitiesLetters(): List<String> =
        getLocalities().map { it.name.first().toString() }.distinct().sorted()

    override fun getLocalitiesNamesByLetter(letter: Char): List<String> {
        val query = Query(
                Criteria
                    .where(PROPERTY_LOCALITY_NAME)
                    .regex(Pattern.compile("^$letter.*", Pattern.CASE_INSENSITIVE))
        )
        return mongoOperation.find(query, Locality::class.java).map { it.name }
    }

    override fun getLocalityByName(name: String): Locality? =
        mongoOperation.findOne(queryLocalityName(name), Locality::class.java)

    private fun queryLocalityName(name: String): Query =
        Query(Criteria.where(PROPERTY_LOCALITY_NAME).`is`(name))
}