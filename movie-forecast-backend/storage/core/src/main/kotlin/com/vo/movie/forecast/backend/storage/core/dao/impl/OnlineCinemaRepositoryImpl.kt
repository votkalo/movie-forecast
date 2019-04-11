package com.vo.movie.forecast.backend.storage.core.dao.impl

import com.vo.movie.forecast.backend.storage.core.dao.OnlineCinemaRepository
import com.vo.movie.forecast.backend.storage.core.document.MovieAccess
import com.vo.movie.forecast.backend.storage.core.document.MovieAccess.Companion.PROPERTY_ACCESS_ORIGINAL_TITLE
import com.vo.movie.forecast.backend.storage.core.document.MovieAccess.Companion.PROPERTY_ACCESS_TITLE
import com.vo.movie.forecast.backend.storage.core.document.MovieAccess.Companion.PROPERTY_ACCESS_YEAR
import com.vo.movie.forecast.backend.storage.core.document.MovieInfo
import com.vo.movie.forecast.backend.storage.core.document.OnlineCinema
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Repository

@Repository
open class OnlineCinemaRepositoryImpl(private val mongoOperation: MongoOperations) : OnlineCinemaRepository {

    override fun getMovieAccess(onlineCinema: OnlineCinema, movieInfo: MovieInfo): MovieAccess? {
        val query = Query(Criteria.where(PROPERTY_ACCESS_TITLE).`is`(movieInfo.title))
        if (movieInfo.originalTitle != null) query.addCriteria(
                Criteria.where(PROPERTY_ACCESS_ORIGINAL_TITLE).`is`(
                        movieInfo.originalTitle
                )
        )
        if (movieInfo.year != null) query.addCriteria(Criteria.where(PROPERTY_ACCESS_YEAR).`is`(movieInfo.year))
        return mongoOperation.findOne(query, MovieAccess::class.java, onlineCinema.value)
    }

    override fun save(onlineCinema: OnlineCinema, movieAccess: MovieAccess) {
        mongoOperation.save(movieAccess, onlineCinema.value)
    }
}