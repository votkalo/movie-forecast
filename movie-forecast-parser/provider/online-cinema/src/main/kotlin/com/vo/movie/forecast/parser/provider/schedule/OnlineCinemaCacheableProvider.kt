package com.vo.movie.forecast.parser.provider.schedule

import com.vo.movie.forecast.parser.api.online.cinema.OnlineCinemaApi
import com.vo.movie.forecast.parser.dto.online.cinema.MovieAccessInfoDTO
import com.vo.movie.forecast.parser.dto.online.cinema.MovieInfoDTO
import com.vo.movie.forecast.parser.dto.online.cinema.OnlineCinema
import com.vo.movie.forecast.parser.provider.schedule.configuration.OnlineCinemaCacheConfiguration.Companion.ONLINE_CINEMA_MOVIE_ACCESS_INFO_CACHE_NAME
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Component

@Component
open class OnlineCinemaCacheableProvider(private val onlineCinemaApi: OnlineCinemaApi) : OnlineCinemaProvider {

    @Cacheable(cacheNames = [ONLINE_CINEMA_MOVIE_ACCESS_INFO_CACHE_NAME])
    override fun getMovieAccessInfo(onlineCinema: OnlineCinema, movieInfo: MovieInfoDTO): MovieAccessInfoDTO =
        onlineCinemaApi.getMovieAccessInfo(onlineCinema, movieInfo)
}