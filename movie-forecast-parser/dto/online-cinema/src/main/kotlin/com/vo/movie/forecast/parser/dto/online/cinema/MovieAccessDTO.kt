package com.vo.movie.forecast.parser.dto.online.cinema

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

data class MovieAccessDTO(val title: String,
                          val originalTitle: String?,
                          val year: String?,
                          val price: BigDecimal,
                          val currency: String,
                          @get:JsonProperty("isAllowBySubscription") val isAllowBySubscription: Boolean,
                          @get:JsonProperty("isPreOrder") val isPreOrder: Boolean)