package com.vo.movie.forecast.notifier.worker

import com.vo.movie.forecast.backend.api.notifier.MovieApi
import com.vo.movie.forecast.backend.api.notifier.UserApi
import com.vo.movie.forecast.backend.data.MovieInfo
import com.vo.movie.forecast.backend.data.Notification
import com.vo.movie.forecast.backend.data.UserInfo
import com.vo.movie.forecast.bot.api.NotificationApi
import com.vo.movie.forecast.parser.provider.schedule.ScheduleProvider
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class CinemaNotifier(private val userApi: UserApi,
                     private val movieApi: MovieApi,
                     private val scheduleProvider: ScheduleProvider,
                     private val notificationApi: NotificationApi) {

    @Scheduled(cron = "0 29 23 * * ?", zone = "Europe/Minsk")
    fun notifyUsersAboutMoviesInCinema() {
        var userPage = 0
        var moviePage = 0
        val pageSize = 50
        var users: List<UserInfo>
        do {
            users = userApi.getUsersWithoutMovies(userPage++, pageSize)
            users.forEach { user ->
                val moviesSchedule = scheduleProvider.getMovieSchedule(user.locality.alternativeName)
                var movies: List<MovieInfo>
                do {
                    movies = movieApi.getUserMovies(user.userId, moviePage++, pageSize)
                    movies.forEach { movie ->
                        moviesSchedule.forEach { movieSchedule ->
                            if (movie.title == movieSchedule.title) {
                                notificationApi.sendNotification(
                                        //TODO: create full message with schedule
                                        Notification(
                                                user.userId,
                                                movie.title
                                        )
                                )
                            }
                        }
                    }
                } while (movies.isNotEmpty())
            }
        } while (users.isNotEmpty())
    }

}