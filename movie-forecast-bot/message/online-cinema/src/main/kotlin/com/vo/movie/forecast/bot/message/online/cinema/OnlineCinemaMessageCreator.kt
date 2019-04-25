package com.vo.movie.forecast.bot.message.online.cinema

import com.vo.movie.forecast.parser.dto.online.cinema.MovieAccessDTO
import com.vo.movie.forecast.parser.dto.online.cinema.OnlineCinemaDTO

interface OnlineCinemaMessageCreator {

    fun createMoviesAccess(onlineCinema: OnlineCinemaDTO,
                           notificationMovieAccessInfoList: List<MovieAccessDTO>): String
}