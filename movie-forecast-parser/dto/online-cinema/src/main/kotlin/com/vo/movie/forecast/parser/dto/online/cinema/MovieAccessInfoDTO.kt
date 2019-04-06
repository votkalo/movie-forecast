package com.vo.movie.forecast.parser.dto.online.cinema

import java.math.BigDecimal

data class MovieAccessInfoDTO(val title: String,
                              val originalTitle: String?,
                              val year: String?,
                              val price: BigDecimal,
                              val currency: String,
                              val isAllowBySubscription: Boolean,
                              val isPreOrder: Boolean)