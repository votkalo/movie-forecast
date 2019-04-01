package com.vo.movie.forecast.backend.api.bot

import com.vo.movie.forecast.commons.data.Locality

interface UserApi {

    fun updateLocality(userId: Long, locality: Locality)

    fun removeLocality(userId: Long)
}