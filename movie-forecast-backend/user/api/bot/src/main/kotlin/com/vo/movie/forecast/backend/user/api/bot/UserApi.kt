package com.vo.movie.forecast.backend.user.api.bot

import com.vo.movie.forecast.commons.data.Locality

interface UserApi {

    fun updateLocality(userId: Long, locality: Locality)

    fun removeLocality(userId: Long)
}