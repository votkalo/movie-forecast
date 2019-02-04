package com.vo.movie.forecast.backend.core.dao.impl

import com.vo.movie.forecast.backend.core.dao.UserRepository
import com.vo.movie.forecast.backend.core.entity.PROPERTY_USER_KINOPOISK_MOVIE_IDS
import com.vo.movie.forecast.backend.core.entity.PROPERTY_USER_USER_ID
import com.vo.movie.forecast.backend.core.entity.User
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.data.mongodb.core.upsert
import org.springframework.stereotype.Repository

@Repository
open class UserRepositoryImpl(private val mongoOperation: MongoOperations) : UserRepository {

    override fun registerMovie(userId: Long, kinopoiskMovieId: Long) {
        val query = Query()
        query.addCriteria(Criteria.where(PROPERTY_USER_USER_ID).`is`(userId))

        val update = Update()
        update.addToSet(PROPERTY_USER_KINOPOISK_MOVIE_IDS, kinopoiskMovieId)

        mongoOperation.upsert(query, update, User::class)
    }
}