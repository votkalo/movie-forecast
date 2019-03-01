package com.vo.movie.forecast.notifier.worker

import com.vo.movie.forecast.backend.api.notifier.MovieApi
import com.vo.movie.forecast.backend.api.notifier.UserApi
import com.vo.movie.forecast.backend.data.MovieInfo
import com.vo.movie.forecast.backend.data.Notification
import com.vo.movie.forecast.backend.data.UserInfo
import com.vo.movie.forecast.bot.api.NotificationApi
import com.vo.movie.forecast.parser.api.schedule.dto.MovieSchedule
import com.vo.movie.forecast.parser.provider.schedule.ScheduleProvider
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class MovieScheduleNotifier(private val userApi: UserApi,
                            private val movieApi: MovieApi,
                            private val scheduleProvider: ScheduleProvider,
                            private val notificationApi: NotificationApi) {

    @Scheduled(cron = "0 36 15 * * ?", zone = "Europe/Minsk")
    fun notifyUsersAboutMoviesInCinema() {
        var userPage = 0
        val pageSize = 50
        var users: List<UserInfo>
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

    private fun sendNotification(user: UserInfo, notificationMoviesSchedule: List<MovieSchedule>) {
        notificationApi.sendNotification(Notification(user.userId, createMessageText(user.locality.name, notificationMoviesSchedule)))
    }

    private fun createMessageText(localityName: String, notificationMoviesSchedule: List<MovieSchedule>): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append("Расписание отслеживаемых фильмов на сегодня в <b>$localityName</b>:\n\n")
        notificationMoviesSchedule.forEach {
            val title = it.title
            val originalTitle = it.originalTitle
            stringBuilder.append("<b>$title</b>")
            if (originalTitle != null && originalTitle.isNotBlank()) {
                stringBuilder.append("\n<i>$originalTitle</i>")
            }
            it.cinemas.forEach { cinema ->
                stringBuilder.append("\n• ${cinema.name}")
                cinema.sessions.forEach { session ->
                    stringBuilder.append(" <i>${session.time}</i>")
                    if (session.is3D) {
                        stringBuilder.append("<i>(3D)</i>")
                    }
                }
            }
            stringBuilder.append("\n\n")
        }
        return stringBuilder.toString()
    }

    private fun collectNotificationMoviesSchedule(user: UserInfo): List<MovieSchedule> {
        var moviePage = 0
        val pageSize = 50
        val notificationMoviesSchedule = ArrayList<MovieSchedule>()
        val moviesSchedule = scheduleProvider.getMovieSchedule(user.locality.alternativeName)
        var movies: List<MovieInfo>
        do {
            movies = movieApi.getUserMovies(user.userId, moviePage++, pageSize)
            notificationMoviesSchedule.addAll(moviesSchedule.filter { it.isScheduleMatch(movies) })
        } while (movies.size == pageSize)
        return notificationMoviesSchedule
    }

    private fun MovieSchedule.isScheduleMatch(movies: List<MovieInfo>): Boolean = movies.any { it.isEqualsSchedule(this) }

    private fun MovieInfo.isEqualsSchedule(movieSchedule: MovieSchedule): Boolean {
        return removeAllExceptDigitsLetters(title).equals(removeAllExceptDigitsLetters(movieSchedule.title), true)
                && year == movieSchedule.year
    }

    private fun removeAllExceptDigitsLetters(value: String?): String? {
        return value?.replace(Regex("[\\s\\\\\\-'\"@#\$%^&*()+=<>/`~!?;:.,_]"), "")
    }
}