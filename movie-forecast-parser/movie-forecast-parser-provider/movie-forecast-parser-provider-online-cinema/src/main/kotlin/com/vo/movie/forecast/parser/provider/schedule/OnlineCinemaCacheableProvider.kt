package com.vo.movie.forecast.parser.provider.schedule

import com.vo.movie.forecast.commons.data.MovieInfo
import com.vo.movie.forecast.parser.api.online.cinema.OnlineCinemaApi
import com.vo.movie.forecast.parser.api.online.cinema.dto.MovieAccessInfo
import com.vo.movie.forecast.parser.api.online.cinema.dto.OnlineCinema
import com.vo.movie.forecast.parser.provider.schedule.configuration.OnlineCinemaCacheConfiguration.Companion.ONLINE_CINEMA_MOVIE_ACCESS_INFO_CACHE_NAME
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Component

@Component
open class OnlineCinemaCacheableProvider(private val onlineCinemaApi: OnlineCinemaApi) : OnlineCinemaProvider {

    @Cacheable(cacheNames = [ONLINE_CINEMA_MOVIE_ACCESS_INFO_CACHE_NAME])
    override fun getMovieAccessInfo(onlineCinema: OnlineCinema, movieInfo: MovieInfo): MovieAccessInfo = onlineCinemaApi.getMovieAccessInfo(onlineCinema, movieInfo)
}