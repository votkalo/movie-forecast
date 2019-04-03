package com.vo.movie.forecast.bot.rest

import com.vo.movie.forecast.bot.data.NotificationDTO
import com.vo.movie.forecast.bot.service.NotificationService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/notifications")
class NotificationController(private val notificationService: NotificationService) {

    @PostMapping
    fun sendNotification(@RequestBody notification: NotificationDTO) {
        notificationService.sendNotification(notification)
    }
}