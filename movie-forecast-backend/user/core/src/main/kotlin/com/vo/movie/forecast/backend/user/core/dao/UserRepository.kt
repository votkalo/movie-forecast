package com.vo.movie.forecast.backend.user.core.dao

import com.vo.movie.forecast.backend.user.data.UserInfo
import com.vo.movie.forecast.commons.data.Locality

interface UserRepository {

    fun updateLocality(userId: Long, locality: Locality)

    fun removeLocality(userId: Long)

    fun getUsersInfoWithLocality(page: Int, size: Int): List<UserInfo>

    fun getUsersIds(page: Int, size: Int): List<Long>
}