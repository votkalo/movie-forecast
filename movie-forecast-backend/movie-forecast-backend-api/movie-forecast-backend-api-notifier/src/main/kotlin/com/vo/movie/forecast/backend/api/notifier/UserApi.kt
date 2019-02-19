package com.vo.movie.forecast.backend.api.notifier

import com.vo.movie.forecast.backend.data.UserInfo

interface UserApi {

    fun getUsersWithoutMovies(page: Int, size: Int): List<UserInfo>
}