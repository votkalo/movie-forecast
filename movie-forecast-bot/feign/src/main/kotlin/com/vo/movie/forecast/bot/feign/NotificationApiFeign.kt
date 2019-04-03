package com.vo.movie.forecast.bot.feign

import com.vo.movie.forecast.bot.api.NotificationApi
import com.vo.movie.forecast.bot.data.NotificationDTO
import feign.RequestLine

interface NotificationApiFeign : NotificationApi {

    @RequestLine("POST /notifications")
    override fun sendNotification(notification: NotificationDTO)
}