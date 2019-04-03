package com.vo.movie.forecast.backend.user.core.document

import com.vo.movie.forecast.backend.user.core.document.User.Companion.DOCUMENT_USER_NAME
import org.springframework.data.mongodb.core.mapping.Document

@Document(DOCUMENT_USER_NAME)
data class User(
    val userId: Long,
    val locality: Locality?,
    val movies: MutableList<Movie> = ArrayList(),
    val moviesLetters: MutableList<String> = ArrayList()
) {
    companion object {
        const val DOCUMENT_USER_NAME = "users"
        const val PROPERTY_USER_USER_ID = "userId"
        const val PROPERTY_USER_LOCALITY = "locality"
        const val PROPERTY_USER_MOVIES = "movies"
        const val PROPERTY_USER_MOVIES_LETTERS = "moviesLetters"
    }
}
