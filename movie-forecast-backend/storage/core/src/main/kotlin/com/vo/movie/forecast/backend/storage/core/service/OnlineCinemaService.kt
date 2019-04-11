package com.vo.movie.forecast.backend.storage.core.service

import com.vo.movie.forecast.parser.dto.online.cinema.MovieAccessDTO
import com.vo.movie.forecast.parser.dto.online.cinema.MovieInfoDTO
import com.vo.movie.forecast.parser.dto.online.cinema.OnlineCinemaDTO

interface OnlineCinemaService {

    fun getMovieAccess(onlineCinema: OnlineCinemaDTO, movieInfo: MovieInfoDTO): MovieAccessDTO
}