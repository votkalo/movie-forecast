package com.vo.movie.forecast.bot.feign

import com.vo.movie.forecast.bot.data.Notification
import com.vo.movie.forecast.bot.api.NotificationApi
import feign.RequestLine

interface NotificationApiFeign : NotificationApi {

    @RequestLine("POST /notifications")
    override fun sendNotification(notification: Notification)
}