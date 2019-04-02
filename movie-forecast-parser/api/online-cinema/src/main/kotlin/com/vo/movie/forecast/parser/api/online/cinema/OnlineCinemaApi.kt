package com.vo.movie.forecast.parser.api.online.cinema

import com.vo.movie.forecast.commons.data.MovieInfo
import com.vo.movie.forecast.parser.api.online.cinema.dto.MovieAccessInfo
import com.vo.movie.forecast.parser.api.online.cinema.dto.OnlineCinema

interface OnlineCinemaApi {

    fun getMovieAccessInfo(onlineCinemaName: OnlineCinema, movieInfo: MovieInfo): MovieAccessInfo
}