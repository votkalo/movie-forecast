package com.vo.movie.forecast.backend.core.dao.impl

import com.vo.movie.forecast.backend.core.dao.MovieRepository
import com.vo.movie.forecast.backend.core.document.User.Companion.DOCUMENT_USER__NAME
import com.vo.movie.forecast.backend.core.document.User.Companion.PROPERTY_USER_MOVIES
import com.vo.movie.forecast.backend.core.document.User.Companion.PROPERTY_USER_USER_ID
import com.vo.movie.forecast.backend.data.MovieInfo
import com.vo.movie.forecast.commons.data.Movie.Companion.PROPERTY_MOVIE_ORIGINAL_TITLE
import com.vo.movie.forecast.commons.data.Movie.Companion.PROPERTY_MOVIE_TITLE
import com.vo.movie.forecast.commons.data.Movie.Companion.PROPERTY_MOVIE_YEAR
import org.springframework.data.domain.PageRequest
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.aggregation.Aggregation
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.stereotype.Repository

@Repository
open class MovieRepositoryImpl(private val mongoOperation: MongoOperations) : MovieRepository {

    override fun getUserMovies(userId: Long, page: Int, size: Int): List<MovieInfo> {
        val matchStage = Aggregation.match(Criteria.where(PROPERTY_USER_USER_ID).`is`(userId))
        val unwindStage = Aggregation.unwind(PROPERTY_USER_MOVIES)
        val projectStage = Aggregation.project()
                .andExpression("$PROPERTY_USER_MOVIES.$PROPERTY_MOVIE_TITLE").`as`(PROPERTY_MOVIE_TITLE)
                .andExpression("$PROPERTY_USER_MOVIES.$PROPERTY_MOVIE_ORIGINAL_TITLE").`as`(PROPERTY_MOVIE_ORIGINAL_TITLE)
                .andExpression("$PROPERTY_USER_MOVIES.$PROPERTY_MOVIE_YEAR").`as`(PROPERTY_MOVIE_YEAR)
        val pageRequest = PageRequest.of(page, size)
        val skipStage = Aggregation.skip(pageRequest.offset)
        val limitStage = Aggregation.limit(pageRequest.pageSize.toLong())
        val aggregation = Aggregation.newAggregation(matchStage, unwindStage, projectStage, skipStage, limitStage)
        return mongoOperation.aggregate(aggregation, DOCUMENT_USER__NAME, MovieInfo::class.java).mappedResults
    }
}