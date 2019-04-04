package com.vo.movie.forecast.backend.storage.core.document

import com.vo.movie.forecast.backend.storage.core.document.Locality.Companion.DOCUMENT_LOCALITY_NAME
import org.springframework.data.mongodb.core.mapping.Document

@Document(DOCUMENT_LOCALITY_NAME)
data class Locality(val name: String,
                    val alternativeName: String,
                    val moviesScheduleURL: String) {
    companion object {
        const val DOCUMENT_LOCALITY_NAME = "localities"
        const val PROPERTY_LOCALITY_NAME = "name"
        const val PROPERTY_LOCALITY_ALTERNATIVE_NAME = "alternativeName"
        const val PROPERTY_LOCALITY_MOVIES_SCHEDULE_URL = "moviesScheduleURL"
    }
}