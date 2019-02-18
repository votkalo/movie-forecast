package com.vo.movie.forecast.notifier.worker

import com.vo.movie.forecast.backend.api.notifier.UserApi
import com.vo.movie.forecast.backend.data.Notification
import com.vo.movie.forecast.backend.data.User
import com.vo.movie.forecast.bot.api.NotificationApi
import com.vo.movie.forecast.parser.provider.schedule.ScheduleProvider
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class CinemaNotifier(private val userApi: UserApi,
                     private val scheduleProvider: ScheduleProvider,
                     private val notificationApi: NotificationApi) {

    @Scheduled(cron = "0 24 23 * * ?", zone = "Europe/Minsk")
    fun notifyUsersAboutMoviesInCinema() {
        var page = 0
        val size = 10
        var users: List<User>
        do {
            //TODO: create getShotUsers info with locality and id
            //TODO: create movieApi and get moviesByUserId with pagination
            users = userApi.getUsers(page++, size)
            users.forEach { user ->
                val moviesSchedule = scheduleProvider.getMovieSchedule(user.locality.alternativeName)
                user.movies.forEach { movie ->
                    moviesSchedule.forEach { movieSchedule ->
                        if (movie.title == movieSchedule.title) {
                            notificationApi.sendNotification(
                                    //TODOD: create full message with schedule
                                    Notification(
                                            user.userId,
                                            movie.title
                                    )
                            )
                        }
                    }
                }
            }
        } while (users.isNotEmpty())
    }
}