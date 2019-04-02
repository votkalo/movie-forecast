package com.vo.movie.forecast.backend.user.api.notifier

import com.vo.movie.forecast.backend.user.data.UserInfo

interface UserApi {

    fun getUsersInfoWithLocality(page: Int, size: Int): List<UserInfo>

    fun getUsersIds(page: Int, size: Int): List<Long>
}