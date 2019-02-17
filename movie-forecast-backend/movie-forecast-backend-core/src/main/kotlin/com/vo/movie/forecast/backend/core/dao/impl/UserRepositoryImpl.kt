package com.vo.movie.forecast.backend.core.dao.impl

import com.vo.movie.forecast.backend.core.dao.UserRepository
import com.vo.movie.forecast.backend.data.User
import com.vo.movie.forecast.backend.data.User.Companion.PROPERTY_USER_LOCALITY
import com.vo.movie.forecast.backend.data.User.Companion.PROPERTY_USER_MOVIES
import com.vo.movie.forecast.backend.data.User.Companion.PROPERTY_USER_USER_ID
import com.vo.movie.forecast.commons.data.Locality
import com.vo.movie.forecast.commons.data.Movie
import org.springframework.data.domain.PageRequest
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.exists
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.data.mongodb.core.upsert
import org.springframework.stereotype.Repository

@Repository
open class UserRepositoryImpl(private val mongoOperation: MongoOperations) : UserRepository {

    override fun existsMovie(userId: Long, kinopoiskMovieId: Long): Boolean {
        val query = Query()
        query.addCriteria(Criteria.where(PROPERTY_USER_USER_ID).`is`(userId))
        query.addCriteria(Criteria.where("movies.kinopoiskMovieId").`is`(kinopoiskMovieId))
        return mongoOperation.exists(query, User::class)
    }

    override fun registerMovie(userId: Long, movie: Movie) {
        val query = findUserQuery(userId)
        val update = Update()
        update.addToSet(PROPERTY_USER_MOVIES, movie)
        mongoOperation.upsert(query, update, User::class)
    }

    override fun updateLocality(userId: Long, locality: Locality) {
        val query = findUserQuery(userId)
        val update = Update()
        update.set(PROPERTY_USER_LOCALITY, locality)
        mongoOperation.upsert(query, update, User::class)
    }

    override fun getUsers(page: Int, size: Int): List<User> {
        val pageRequest = PageRequest.of(page, size)
        return mongoOperation.find(Query().with(pageRequest), User::class.java)
    }

    private fun findUserQuery(userId: Long): Query {
        val query = Query()
        query.addCriteria(Criteria.where(PROPERTY_USER_USER_ID).`is`(userId))
        return query
    }
}