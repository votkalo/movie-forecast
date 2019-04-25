package com.vo.movie.forecast.notifier.worker

import com.vo.movie.forecast.backend.storage.api.OnlineCinemaApi
import com.vo.movie.forecast.backend.user.api.UserApi
import com.vo.movie.forecast.backend.user.api.UserMovieApi
import com.vo.movie.forecast.bot.api.NotificationApi
import com.vo.movie.forecast.bot.data.NotificationDTO
import com.vo.movie.forecast.bot.message.online.cinema.OnlineCinemaMessageCreator
import com.vo.movie.forecast.parser.dto.movie.MovieDTO
import com.vo.movie.forecast.parser.dto.online.cinema.MovieAccessDTO
import com.vo.movie.forecast.parser.dto.online.cinema.MovieInfoDTO
import com.vo.movie.forecast.parser.dto.online.cinema.OnlineCinemaDTO
import feign.FeignException
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class OnlineCinemaNotifier(private val userApi: UserApi,
                           private val userMovieApi: UserMovieApi,
                           private val onlineCinemaApi: OnlineCinemaApi,
                           private val notificationApi: NotificationApi,
                           private val onlineCinemaMessageCreator: OnlineCinemaMessageCreator) {

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
                var movies: List<MovieDTO>
                val onlineCinemaMovieAccessMap: MutableMap<OnlineCinemaDTO, MutableList<MovieAccessDTO>> = HashMap()
                do {
                    movies = userMovieApi.getUserMovies(userId, moviePage++, pageSize)
                    movies.forEach { movieInfo ->
                        OnlineCinemaDTO.values().forEach { onlineCinema ->
                            if (onlineCinemaMovieAccessMap[onlineCinema] == null) {
                                onlineCinemaMovieAccessMap[onlineCinema] = ArrayList()
                            }
                            try {
                                onlineCinemaMovieAccessMap[onlineCinema]?.add(
                                        onlineCinemaApi.getMovieAccessInfo(
                                                MovieInfoDTO(movieInfo.title, movieInfo.originalTitle, movieInfo.year),
                                                onlineCinema
                                        )

                                )
                            } catch (movieNotFoundException: FeignException) {
                                //Catch if movie not found in online cinema
                            }
                        }
                    }
                } while (movies.size == pageSize)
                onlineCinemaMovieAccessMap.forEach { (onlineCinema, movieAccessInfoList) ->
                    notificationApi.sendNotification(
                            NotificationDTO(userId, onlineCinemaMessageCreator.createMoviesAccess(onlineCinema, movieAccessInfoList))
                    )
                }
            }
        } while (usersIds.size == pageSize)
    }
}