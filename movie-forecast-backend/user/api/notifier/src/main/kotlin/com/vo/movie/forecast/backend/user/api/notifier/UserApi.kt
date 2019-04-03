package com.vo.movie.forecast.backend.user.api.notifier

import com.vo.movie.forecast.backend.user.data.UserWithLocalityInfoDTO

interface UserApi {

    fun getUsersInfoWithLocality(page: Int, size: Int): List<UserWithLocalityInfoDTO>

    fun getUsersIds(page: Int, size: Int): List<Long>
}