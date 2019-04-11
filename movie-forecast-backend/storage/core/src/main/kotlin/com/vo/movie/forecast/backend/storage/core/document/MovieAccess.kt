package com.vo.movie.forecast.backend.storage.core.document

import java.math.BigDecimal

data class MovieAccess(val title: String,
                       val originalTitle: String?,
                       val year: String?,
                       val price: BigDecimal,
                       val currency: String,
                       val isAllowBySubscription: Boolean,
                       val isPreOrder: Boolean) {
    companion object {
        const val PROPERTY_ACCESS_TITLE = "title"
        const val PROPERTY_ACCESS_ORIGINAL_TITLE = "originalTitle"
        const val PROPERTY_ACCESS_YEAR = "year"
    }
}