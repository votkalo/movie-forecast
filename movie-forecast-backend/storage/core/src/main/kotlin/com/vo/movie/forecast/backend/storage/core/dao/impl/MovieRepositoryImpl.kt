package com.vo.movie.forecast.backend.storage.core.dao.impl

import com.vo.movie.forecast.backend.storage.core.dao.MovieRepository
import com.vo.movie.forecast.backend.storage.core.document.Movie
import com.vo.movie.forecast.backend.storage.core.document.Movie.Companion.PROPERTY_MOVIE_BIG_POSTER_URL
import com.vo.movie.forecast.backend.storage.core.document.Movie.Companion.PROPERTY_MOVIE_COUNTRIES
import com.vo.movie.forecast.backend.storage.core.document.Movie.Companion.PROPERTY_MOVIE_GENRES
import com.vo.movie.forecast.backend.storage.core.document.Movie.Companion.PROPERTY_MOVIE_KINOPOISK_MOVIE_ID
import com.vo.movie.forecast.backend.storage.core.document.Movie.Companion.PROPERTY_MOVIE_KINOPOISK_RATING
import com.vo.movie.forecast.backend.storage.core.document.Movie.Companion.PROPERTY_MOVIE_ORIGINAL_TITLE
import com.vo.movie.forecast.backend.storage.core.document.Movie.Companion.PROPERTY_MOVIE_SMALL_POSTER_URL
import com.vo.movie.forecast.backend.storage.core.document.Movie.Companion.PROPERTY_MOVIE_SOURCE_URL
import com.vo.movie.forecast.backend.storage.core.document.Movie.Companion.PROPERTY_MOVIE_TITLE
import com.vo.movie.forecast.backend.storage.core.document.Movie.Companion.PROPERTY_MOVIE_YEAR
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.Update
import org.springframework.stereotype.Repository

@Repository
open class MovieRepositoryImpl(private val mongoOperation: MongoOperations) : MovieRepository {

    override fun saveMovie(movie: Movie) {
        val query = Query().addCriteria(Criteria.where(PROPERTY_MOVIE_KINOPOISK_MOVIE_ID).`is`(movie.kinopoiskMovieId))
        val update = Update()
        update.set(PROPERTY_MOVIE_TITLE, movie.title)
        if (movie.originalTitle != null) update.set(PROPERTY_MOVIE_ORIGINAL_TITLE, movie.originalTitle)
        if (movie.year != null) update.set(PROPERTY_MOVIE_YEAR, movie.year)
        if (movie.genres != null) update.set(PROPERTY_MOVIE_GENRES, movie.genres)
        if (movie.countries != null) update.set(PROPERTY_MOVIE_COUNTRIES, movie.countries)
        if (movie.kinopoiskRating != null) update.set(PROPERTY_MOVIE_KINOPOISK_RATING, movie.kinopoiskRating)
        update.set(PROPERTY_MOVIE_BIG_POSTER_URL, movie.bigPosterURL)
        update.set(PROPERTY_MOVIE_SMALL_POSTER_URL, movie.smallPosterURL)
        update.set(PROPERTY_MOVIE_SOURCE_URL, movie.sourceURL)
        mongoOperation.upsert(query, update, Movie::class.java)
    }
}