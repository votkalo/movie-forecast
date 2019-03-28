package com.vo.movie.forecast.backend.core.dao.impl

import com.mongodb.BasicDBObject
import com.vo.movie.forecast.backend.core.dao.MovieRepository
import com.vo.movie.forecast.backend.core.document.User
import com.vo.movie.forecast.backend.core.document.User.Companion.PROPERTY_USER_MOVIES
import com.vo.movie.forecast.backend.core.document.User.Companion.PROPERTY_USER_MOVIES_LETTERS
import com.vo.movie.forecast.commons.data.Movie
import com.vo.movie.forecast.commons.data.Movie.Companion.PROPERTY_MOVIE_BIG_POSTER_URL
import com.vo.movie.forecast.commons.data.Movie.Companion.PROPERTY_MOVIE_COUNTRIES
import com.vo.movie.forecast.commons.data.Movie.Companion.PROPERTY_MOVIE_GENRES
import com.vo.movie.forecast.commons.data.Movie.Companion.PROPERTY_MOVIE_KINOPOISK_MOVIE_ID
import com.vo.movie.forecast.commons.data.Movie.Companion.PROPERTY_MOVIE_KINOPOISK_RATING
import com.vo.movie.forecast.commons.data.Movie.Companion.PROPERTY_MOVIE_ORIGINAL_TITLE
import com.vo.movie.forecast.commons.data.Movie.Companion.PROPERTY_MOVIE_SMALL_POSTER_URL
import com.vo.movie.forecast.commons.data.Movie.Companion.PROPERTY_MOVIE_SOURCE_URL
import com.vo.movie.forecast.commons.data.Movie.Companion.PROPERTY_MOVIE_TITLE
import com.vo.movie.forecast.commons.data.Movie.Companion.PROPERTY_MOVIE_YEAR
import com.vo.movie.forecast.commons.data.MovieInfo
import org.springframework.data.domain.PageRequest
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.aggregation.Aggregation
import org.springframework.data.mongodb.core.aggregation.AggregationOperation
import org.springframework.data.mongodb.core.exists
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.data.mongodb.core.upsert
import org.springframework.stereotype.Repository
import java.util.regex.Pattern

@Repository
open class MovieRepositoryImpl(private val mongoOperation: MongoOperations) : MovieRepository {

    override fun existsMovie(userId: Long, kinopoiskMovieId: Long): Boolean {
        val query = Query()
                .addCriteria(criteriaUserId(userId))
                .addCriteria(kinopoiskMovieIdIdCriteria(kinopoiskMovieId))
        return mongoOperation.exists(query, User::class)
    }

    override fun getMovie(userId: Long, kinopoiskMovieId: Long): MovieInfo {
        val operations = arrayListOf(
                aggregationMatchUserId(userId),
                aggregationUnwindMovies(),
                Aggregation.match(kinopoiskMovieIdIdCriteria(kinopoiskMovieId)),
                aggregationProjectMovieInfo()
        )
        return mongoOperation.aggregate(Aggregation.newAggregation(operations), User::class.java, MovieInfo::class.java).first()
    }

    override fun registerMovie(userId: Long, movie: Movie) {
        mongoOperation.upsert(queryUser(userId), Update().addToSet(PROPERTY_USER_MOVIES, movie), User::class)
    }

    override fun registerMovieLetter(userId: Long, movieLetter: String) {
        mongoOperation.upsert(queryUser(userId), Update().addToSet(PROPERTY_USER_MOVIES_LETTERS, movieLetter), User::class)
    }

    override fun getMovies(userId: Long, page: Int, size: Int): List<MovieInfo> {
        val pageRequest = PageRequest.of(page, size)
        val operations = arrayListOf(
                aggregationMatchUserId(userId),
                aggregationUnwindMovies(),
                aggregationProjectMovieInfo(),
                Aggregation.skip(pageRequest.offset),
                Aggregation.limit(pageRequest.pageSize.toLong())
        )
        return mongoOperation.aggregate(Aggregation.newAggregation(operations), User::class.java, MovieInfo::class.java).mappedResults
    }

    override fun getMoviesLetters(userId: Long): List<String> =
            mongoOperation.findDistinct(queryUser(userId), PROPERTY_USER_MOVIES_LETTERS, User::class.java, String::class.java)

    override fun getMoviesByLetter(userId: Long, letter: Char): List<MovieInfo> {
        val operations = arrayListOf(
                aggregationMatchUserId(userId),
                aggregationUnwindMovies(),
                Aggregation.match(Criteria.where("$PROPERTY_USER_MOVIES.$PROPERTY_MOVIE_TITLE")
                        .regex(Pattern.compile("^$letter.*", Pattern.CASE_INSENSITIVE))),
                aggregationProjectMovieInfo()
        )
        return mongoOperation.aggregate(Aggregation.newAggregation(operations), User::class.java, MovieInfo::class.java).mappedResults
    }

    override fun searchMovie(userId: Long, movieInfo: MovieInfo): Movie {
        val operations = ArrayList<AggregationOperation>()
        operations.add(aggregationMatchUserId(userId))
        operations.add(aggregationUnwindMovies())
        operations.add(Aggregation.match(Criteria.where("$PROPERTY_USER_MOVIES.$PROPERTY_MOVIE_TITLE")
                .regex(Regex.escape(movieInfo.title))))
        if (movieInfo.originalTitle != null) {
            operations.add(Aggregation.match(Criteria.where("$PROPERTY_USER_MOVIES.$PROPERTY_MOVIE_ORIGINAL_TITLE")
                    .regex(Regex.escape(movieInfo.originalTitle!!))))
        }
        if (movieInfo.year != null) {
            operations.add(Aggregation.match(Criteria.where("$PROPERTY_USER_MOVIES.$PROPERTY_MOVIE_YEAR").`is`(movieInfo.year)))
        }
        operations.add(aggregationProjectMovie())
        return mongoOperation.aggregate(Aggregation.newAggregation(operations), User::class.java, Movie::class.java).first()
    }

    override fun deleteMovie(userId: Long, kinopoiskMovieId: Long) {
        val update = Update().pull(PROPERTY_USER_MOVIES, BasicDBObject(PROPERTY_MOVIE_KINOPOISK_MOVIE_ID, kinopoiskMovieId))
        mongoOperation.findAndModify(queryUser(userId), update, User::class.java)
    }

    override fun deleteMovieLetter(userId: Long, movieLetter: String) {
        val update = Update().pull(PROPERTY_USER_MOVIES_LETTERS, movieLetter)
        mongoOperation.findAndModify(queryUser(userId), update, User::class.java)
    }

    private fun kinopoiskMovieIdIdCriteria(kinopoiskMovieId: Long) = Criteria.where("$PROPERTY_USER_MOVIES.$PROPERTY_MOVIE_KINOPOISK_MOVIE_ID").`is`(kinopoiskMovieId)

    private fun aggregationMatchUserId(userId: Long) = Aggregation.match(criteriaUserId(userId))

    private fun aggregationUnwindMovies() = Aggregation.unwind(PROPERTY_USER_MOVIES)

    private fun aggregationProjectMovieInfo() = Aggregation.project()
            .andExpression("$PROPERTY_USER_MOVIES.$PROPERTY_MOVIE_TITLE").`as`(PROPERTY_MOVIE_TITLE)
            .andExpression("$PROPERTY_USER_MOVIES.$PROPERTY_MOVIE_ORIGINAL_TITLE").`as`(PROPERTY_MOVIE_ORIGINAL_TITLE)
            .andExpression("$PROPERTY_USER_MOVIES.$PROPERTY_MOVIE_YEAR").`as`(PROPERTY_MOVIE_YEAR)

    private fun aggregationProjectMovie() = aggregationProjectMovieInfo()
            .andExpression("$PROPERTY_USER_MOVIES.$PROPERTY_MOVIE_GENRES").`as`(PROPERTY_MOVIE_GENRES)
            .andExpression("$PROPERTY_USER_MOVIES.$PROPERTY_MOVIE_COUNTRIES").`as`(PROPERTY_MOVIE_COUNTRIES)
            .andExpression("$PROPERTY_USER_MOVIES.$PROPERTY_MOVIE_KINOPOISK_RATING").`as`(PROPERTY_MOVIE_KINOPOISK_RATING)
            .andExpression("$PROPERTY_USER_MOVIES.$PROPERTY_MOVIE_KINOPOISK_MOVIE_ID").`as`(PROPERTY_MOVIE_KINOPOISK_MOVIE_ID)
            .andExpression("$PROPERTY_USER_MOVIES.$PROPERTY_MOVIE_BIG_POSTER_URL").`as`(PROPERTY_MOVIE_BIG_POSTER_URL)
            .andExpression("$PROPERTY_USER_MOVIES.$PROPERTY_MOVIE_SMALL_POSTER_URL").`as`(PROPERTY_MOVIE_SMALL_POSTER_URL)
            .andExpression("$PROPERTY_USER_MOVIES.$PROPERTY_MOVIE_SOURCE_URL").`as`(PROPERTY_MOVIE_SOURCE_URL)
}