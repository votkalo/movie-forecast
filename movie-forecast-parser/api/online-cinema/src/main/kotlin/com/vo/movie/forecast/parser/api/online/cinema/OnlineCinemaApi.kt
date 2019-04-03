package com.vo.movie.forecast.parser.api.online.cinema

import com.vo.movie.forecast.parser.api.online.cinema.dto.MovieAccessInfoDTO
import com.vo.movie.forecast.parser.api.online.cinema.dto.MovieInfoDTO
import com.vo.movie.forecast.parser.api.online.cinema.dto.OnlineCinema

interface OnlineCinemaApi {

    fun getMovieAccessInfo(onlineCinemaName: OnlineCinema, movieInfo: MovieInfoDTO): MovieAccessInfoDTO
}