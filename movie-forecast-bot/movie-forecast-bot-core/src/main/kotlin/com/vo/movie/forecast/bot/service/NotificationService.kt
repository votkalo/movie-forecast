package com.vo.movie.forecast.bot.service

import com.vo.movie.forecast.backend.data.Notification
import org.springframework.web.bind.annotation.RequestBody

interface NotificationService {

    fun sendNotification(@RequestBody notification: Notification)
}