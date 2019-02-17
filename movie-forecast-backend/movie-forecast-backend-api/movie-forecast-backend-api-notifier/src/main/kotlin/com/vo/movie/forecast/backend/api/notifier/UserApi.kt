package com.vo.movie.forecast.backend.api.notifier

import com.vo.movie.forecast.backend.data.User

interface UserApi {

    fun getUsers(page: Int, size: Int): List<User>
}