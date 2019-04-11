package com.vo.movie.forecast.parser.api.online.cinema

import com.vo.movie.forecast.parser.dto.online.cinema.MovieAccessDTO
import com.vo.movie.forecast.parser.dto.online.cinema.MovieInfoDTO
import com.vo.movie.forecast.parser.dto.online.cinema.OnlineCinemaDTO

interface OnlineCinemaApi {

    fun getMovieAccessInfo(movieInfo: MovieInfoDTO, onlineCinema: OnlineCinemaDTO): MovieAccessDTO?
}