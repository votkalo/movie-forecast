package com.vo.movie.forecast.parser.api.online.cinema

import com.vo.movie.forecast.parser.dto.online.cinema.MovieAccessInfoDTO
import com.vo.movie.forecast.parser.dto.online.cinema.MovieInfoDTO
import com.vo.movie.forecast.parser.dto.online.cinema.OnlineCinema

interface OnlineCinemaApi {

    fun getMovieAccessInfo(onlineCinemaName: OnlineCinema, movieInfo: MovieInfoDTO): MovieAccessInfoDTO
}