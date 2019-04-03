package com.vo.movie.forecast.backend.user.api.bot

import com.vo.movie.forecast.backend.storage.data.LocalityDTO

interface UserApi {

    fun updateLocality(userId: Long, locality: LocalityDTO)

    fun removeLocality(userId: Long)
}