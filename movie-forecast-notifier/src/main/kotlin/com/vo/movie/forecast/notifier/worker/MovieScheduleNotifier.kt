package com.vo.movie.forecast.notifier.worker

import com.vo.movie.forecast.backend.storage.api.ScheduleApi
import com.vo.movie.forecast.backend.storage.data.ScheduleSearchParamsDTO
import com.vo.movie.forecast.backend.user.api.UserApi
import com.vo.movie.forecast.backend.user.api.UserMovieApi
import com.vo.movie.forecast.backend.user.data.UserWithLocalityInfoDTO
import com.vo.movie.forecast.bot.api.NotificationApi
import com.vo.movie.forecast.bot.data.NotificationDTO
import com.vo.movie.forecast.bot.message.schedule.ScheduleMessageCreator
import com.vo.movie.forecast.parser.dto.movie.MovieDTO
import com.vo.movie.forecast.parser.dto.schedule.MovieScheduleDTO
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class MovieScheduleNotifier(private val userApi: UserApi,
                            private val userMovieApi: UserMovieApi,
                            private val scheduleApi: ScheduleApi,
                            private val notificationApi: NotificationApi,
                            private val scheduleMessageCreator: ScheduleMessageCreator) {

    @Scheduled(cron = "0 00 7 * * ?", zone = "Europe/Minsk")
    fun notifyUsersAboutMoviesInCinema() {
        var userPage = 0
        val pageSize = 50
        var users: List<UserWithLocalityInfoDTO>
        do {
            users = userApi.getUsersInfoWithLocality(userPage++, pageSize)
            users.forEach {
                val notificationMoviesSchedule = collectNotificationMoviesSchedule(it)
                if (notificationMoviesSchedule.isNotEmpty()) {
                    sendNotification(it, notificationMoviesSchedule)
                }
            }
        } while (users.size == pageSize)
    }

    private fun sendNotification(user: UserWithLocalityInfoDTO, notificationMoviesSchedule: List<MovieScheduleDTO>) {
        notificationApi.sendNotification(
                NotificationDTO(
                        user.userId,
                        scheduleMessageCreator.createSchedule(user.locality.name, notificationMoviesSchedule)
                )
        )
    }

    private fun collectNotificationMoviesSchedule(user: UserWithLocalityInfoDTO): List<MovieScheduleDTO> {
        var moviePage = 0
        val pageSize = 50
        val notificationMoviesSchedule = ArrayList<MovieScheduleDTO>()
        var movies: List<MovieDTO>
        do {
            movies = userMovieApi.getUserMovies(user.userId, moviePage++, pageSize)
            notificationMoviesSchedule.addAll(
                    scheduleApi.getMoviesSchedule(ScheduleSearchParamsDTO(user.locality, movies))
            )
        } while (movies.size == pageSize)
        return notificationMoviesSchedule
    }
}