package com.vo.movie.forecast.backend.user.core.dao

import com.vo.movie.forecast.backend.user.core.document.Locality
import com.vo.movie.forecast.backend.user.core.document.User

interface UserRepository {

    fun updateLocality(userId: Long, locality: Locality)

    fun removeLocality(userId: Long)

    fun getUsersInfoWithLocality(page: Int, size: Int): List<User>

    fun getUsersIds(page: Int, size: Int): List<Long>
}