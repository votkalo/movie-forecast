package com.vo.movie.forecast.backend.storage.core.document

import com.vo.movie.forecast.backend.storage.core.document.MovieSchedule.Companion.DOCUMENT_SCHEDULE_NAME
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate

@Document(DOCUMENT_SCHEDULE_NAME)
data class MovieSchedule(val title: String,
                         val originalTitle: String?,
                         val year: String?,
                         val scheduleURL: String,
                         val cinemas: List<CinemaSchedule>,
                         val locality: Locality,
                         val date: LocalDate) {
    companion object {
        const val DOCUMENT_SCHEDULE_NAME = "schedules"
        const val PROPERTY_SCHEDULE_LOCALITY = "locality"
        const val PROPERTY_SCHEDULE_DATE = "date"
    }
}