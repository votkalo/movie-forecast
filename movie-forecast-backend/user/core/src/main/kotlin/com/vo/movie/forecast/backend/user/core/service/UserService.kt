package com.vo.movie.forecast.backend.user.core.service

import com.vo.movie.forecast.backend.storage.data.LocalityDTO
import com.vo.movie.forecast.backend.user.data.UserWithLocalityInfoDTO

interface UserService {

    fun updateLocality(userId: Long, locality: LocalityDTO)

    fun removeLocality(userId: Long)

    fun getUsersInfoWithLocality(page: Int, size: Int): List<UserWithLocalityInfoDTO>

    fun getUsersIds(page: Int, size: Int): List<Long>
}