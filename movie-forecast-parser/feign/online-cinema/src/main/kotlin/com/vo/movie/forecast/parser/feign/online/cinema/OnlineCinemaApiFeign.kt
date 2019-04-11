package com.vo.movie.forecast.parser.feign.online.cinema

import com.vo.movie.forecast.parser.api.online.cinema.OnlineCinemaApi
import com.vo.movie.forecast.parser.dto.online.cinema.MovieAccessDTO
import com.vo.movie.forecast.parser.dto.online.cinema.MovieInfoDTO
import com.vo.movie.forecast.parser.dto.online.cinema.OnlineCinemaDTO
import feign.Param
import feign.RequestLine

interface OnlineCinemaApiFeign : OnlineCinemaApi {

    @RequestLine("POST /online-cinemas/{onlineCinema}/search")
    override fun getMovieAccessInfo(movieInfo: MovieInfoDTO, @Param("onlineCinema") onlineCinema: OnlineCinemaDTO): MovieAccessDTO?
}