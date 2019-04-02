package com.vo.movie.forecast.parser.provider.schedule

import com.vo.movie.forecast.commons.data.MovieInfo
import com.vo.movie.forecast.parser.api.online.cinema.dto.MovieAccessInfo
import com.vo.movie.forecast.parser.api.online.cinema.dto.OnlineCinema

interface OnlineCinemaProvider {

    fun getMovieAccessInfo(onlineCinema: OnlineCinema, movieInfo: MovieInfo): MovieAccessInfo
}