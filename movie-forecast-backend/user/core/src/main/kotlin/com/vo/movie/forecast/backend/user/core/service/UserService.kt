package com.vo.movie.forecast.backend.user.core.service

import com.vo.movie.forecast.backend.user.data.UserWithLocalityInfoDTO
import com.vo.movie.forecast.parser.dto.locality.LocalityDTO

interface UserService {

    fun updateLocality(userId: Long, locality: LocalityDTO)

    fun removeLocality(userId: Long)

    fun getUsersInfoWithLocality(page: Int, size: Int): List<UserWithLocalityInfoDTO>

    fun getUsersIds(page: Int, size: Int): List<Long>
}