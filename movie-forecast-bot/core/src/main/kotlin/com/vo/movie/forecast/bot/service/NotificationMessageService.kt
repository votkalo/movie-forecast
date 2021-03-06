package com.vo.movie.forecast.bot.service

import com.vo.movie.forecast.bot.MovieForecastBot
import com.vo.movie.forecast.bot.data.NotificationDTO
import com.vo.movie.forecast.bot.util.createMessage
import org.springframework.stereotype.Service

@Service
class NotificationMessageService(private val movieForecastBotHandler: MovieForecastBot) : NotificationService {

    override fun sendNotification(notification: NotificationDTO) {
        movieForecastBotHandler.execute(createMessage(notification.userId, notification.text))
    }
}