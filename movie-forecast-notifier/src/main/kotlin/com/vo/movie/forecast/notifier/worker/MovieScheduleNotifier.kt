package com.vo.movie.forecast.notifier.worker

import com.vo.movie.forecast.backend.storage.data.MovieDTO
import com.vo.movie.forecast.backend.user.api.notifier.UserApi
import com.vo.movie.forecast.backend.user.api.notifier.UserMovieApi
import com.vo.movie.forecast.backend.user.data.UserWithLocalityInfoDTO
import com.vo.movie.forecast.bot.api.NotificationApi
import com.vo.movie.forecast.bot.data.NotificationDTO
import com.vo.movie.forecast.parser.api.schedule.dto.MovieScheduleDTO
import com.vo.movie.forecast.parser.provider.schedule.ScheduleProvider
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class MovieScheduleNotifier(private val userApi: UserApi,
                            private val userMovieApi: UserMovieApi,
                            private val scheduleProvider: ScheduleProvider,
                            private val notificationApi: NotificationApi) {

    //    @Scheduled(cron = "0 47 23 * * ?", zone = "Europe/Minsk")
    @Scheduled(fixedDelay = 10000L)
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
                NotificationDTO(user.userId, createMessageText(user.locality.name, notificationMoviesSchedule))
        )
    }

    private fun createMessageText(localityName: String, notificationMoviesSchedule: List<MovieScheduleDTO>): String {
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

    private fun collectNotificationMoviesSchedule(user: UserWithLocalityInfoDTO): List<MovieScheduleDTO> {
        var moviePage = 0
        val pageSize = 50
        val notificationMoviesSchedule = ArrayList<MovieScheduleDTO>()
        val moviesSchedule = scheduleProvider.getMovieSchedule(user.locality.alternativeName)
        var movies: List<MovieDTO>
        do {
            movies = userMovieApi.getUserMovies(user.userId, moviePage++, pageSize)
            notificationMoviesSchedule.addAll(moviesSchedule.filter { it.isScheduleMatch(movies) })
        } while (movies.size == pageSize)
        return notificationMoviesSchedule
    }

    private fun MovieScheduleDTO.isScheduleMatch(movieDTOS: List<MovieDTO>): Boolean =
        movieDTOS.any { it.isEqualsSchedule(this) }

    private fun MovieDTO.isEqualsSchedule(movieSchedule: MovieScheduleDTO): Boolean {
        return removeAllExceptDigitsLetters(title).equals(
                removeAllExceptDigitsLetters(movieSchedule.title),
                true
        ) && year == movieSchedule.year
    }

    private fun removeAllExceptDigitsLetters(value: String?): String? {
        return value?.replace(Regex("[\\s\\\\\\-'\"@#\$%^&*()+=<>/`~!?;:.,_]"), "")
    }
}