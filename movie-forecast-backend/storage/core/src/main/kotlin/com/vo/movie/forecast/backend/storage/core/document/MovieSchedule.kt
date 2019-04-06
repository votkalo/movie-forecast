package com.vo.movie.forecast.backend.storage.core.document

import com.vo.movie.forecast.backend.storage.core.document.MovieSchedule.Companion.DOCUMENT_SCHEDULE_NAME
import org.springframework.data.mongodb.core.mapping.Document

@Document(DOCUMENT_SCHEDULE_NAME)
data class MovieSchedule(val title: String,
                         val originalTitle: String?,
                         val year: String,
                         val scheduleURL: String,
                         val cinemas: List<CinemaSchedule>,
                         val locality: Locality) {
    companion object {
        const val DOCUMENT_SCHEDULE_NAME = "movies"
        const val PROPERTY_SCHEDULE_LOCALITY = "locality"
    }
}