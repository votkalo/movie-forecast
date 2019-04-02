package com.vo.movie.forecast.bot.api

import com.vo.movie.forecast.bot.data.Notification

interface NotificationApi {

    fun sendNotification(notification: Notification)
}