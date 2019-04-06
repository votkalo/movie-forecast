package com.vo.movie.forecast.parser.provider.schedule

import com.vo.movie.forecast.parser.dto.online.cinema.MovieAccessInfoDTO
import com.vo.movie.forecast.parser.dto.online.cinema.MovieInfoDTO
import com.vo.movie.forecast.parser.dto.online.cinema.OnlineCinema

interface OnlineCinemaProvider {

    fun getMovieAccessInfo(onlineCinema: OnlineCinema, movieInfo: MovieInfoDTO): MovieAccessInfoDTO
}