package com.vo.movie.forecast.notifier.worker

import com.vo.movie.forecast.backend.api.notifier.MovieApi
import com.vo.movie.forecast.backend.api.notifier.UserApi
import com.vo.movie.forecast.backend.data.Notification
import com.vo.movie.forecast.bot.api.NotificationApi
import com.vo.movie.forecast.commons.data.MovieInfo
import com.vo.movie.forecast.parser.api.online.cinema.dto.MovieAccessInfo
import com.vo.movie.forecast.parser.api.online.cinema.dto.OnlineCinema
import com.vo.movie.forecast.parser.provider.schedule.OnlineCinemaProvider
import feign.FeignException
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.math.BigDecimal

@Component
class OnlineCinemaNotifier(private val userApi: UserApi,
                           private val movieApi: MovieApi,
                           private val onlineCinemaProvider: OnlineCinemaProvider,
                           private val notificationApi: NotificationApi) {

    //    @Scheduled(cron = "0 31 18 * * ?", zone = "Europe/Minsk")
    @Scheduled(fixedDelay = 10000L)
    fun notifyUsersAboutMoviesInCinema() {
        var userPage = 0
        val pageSize = 50
        var usersIds: List<Long>
        do {
            var moviePage = 0
            usersIds = userApi.getUsersIds(userPage++, pageSize)
            usersIds.forEach { userId ->
                var movies: List<MovieInfo>
                val onlineCinemaMovieAccessInfoMap: MutableMap<OnlineCinema, MutableList<MovieAccessInfo>> = HashMap()
                do {
                    movies = movieApi.getUserMovies(userId, moviePage++, pageSize)
                    movies.forEach { movieInfo ->
                        OnlineCinema.values().forEach { onlineCinema ->
                            if (onlineCinemaMovieAccessInfoMap[onlineCinema] == null) {
                                onlineCinemaMovieAccessInfoMap[onlineCinema] = ArrayList()
                            }
                            try {
                                onlineCinemaMovieAccessInfoMap[onlineCinema]?.add(onlineCinemaProvider.getMovieAccessInfo(onlineCinema, movieInfo))
                            } catch (movieNotFoundException: FeignException) {
                                //Catch if movie not found in online cinema
                            }
                        }
                    }
                } while (movies.size == pageSize)
                onlineCinemaMovieAccessInfoMap.forEach { (onlineCinema, movieAccessInfoList) ->
                    notificationApi.sendNotification(Notification(userId, createMessageText(onlineCinema, movieAccessInfoList)))
                }
            }
        } while (usersIds.size == pageSize)
    }

    private fun createMessageText(onlineCinema: OnlineCinema, notificationMovieAccessInfoList: List<MovieAccessInfo>): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append("Информация отслеживаемых фильмов в онлайн-кинотеатре <b>${onlineCinema.value}</b>:\n\n")
        notificationMovieAccessInfoList.forEach {
            val title = it.title
            val year = it.year
            val originalTitle = it.originalTitle
            stringBuilder.append("<b>")
            stringBuilder.append(title)
            if (year != null) {
                stringBuilder.append(" ($year)")
            }
            stringBuilder.append("</b>")
            if (originalTitle != null && originalTitle.isNotBlank()) {
                stringBuilder.append("\n<i>$originalTitle</i>")
            }
            stringBuilder.append("\n")
            if (it.isAllowBySubscription) {
                stringBuilder.append("Подписка ")
            }
            if (it.price.compareTo(BigDecimal.ZERO) == 1) {
                stringBuilder.append("Покупка(от ${it.price} ${it.currency}) ")
            }
            if (it.isPreOrder) {
                stringBuilder.append("Предзаказ ")
            }
            if (!it.isAllowBySubscription && !it.isPreOrder && it.price.compareTo(BigDecimal.ZERO) == 0) {
                stringBuilder.append("Бесплатно ")
            }
            stringBuilder.append("\n\n")
        }
        return stringBuilder.toString()
    }
}