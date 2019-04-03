package com.vo.movie.forecast.parser.provider.schedule

import com.vo.movie.forecast.parser.api.online.cinema.dto.MovieAccessInfoDTO
import com.vo.movie.forecast.parser.api.online.cinema.dto.MovieInfoDTO
import com.vo.movie.forecast.parser.api.online.cinema.dto.OnlineCinema

interface OnlineCinemaProvider {

    fun getMovieAccessInfo(onlineCinema: OnlineCinema, movieInfo: MovieInfoDTO): MovieAccessInfoDTO
}