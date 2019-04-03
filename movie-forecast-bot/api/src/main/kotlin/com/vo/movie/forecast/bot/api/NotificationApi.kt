package com.vo.movie.forecast.bot.api

import com.vo.movie.forecast.bot.data.NotificationDTO

interface NotificationApi {

    fun sendNotification(notification: NotificationDTO)
}