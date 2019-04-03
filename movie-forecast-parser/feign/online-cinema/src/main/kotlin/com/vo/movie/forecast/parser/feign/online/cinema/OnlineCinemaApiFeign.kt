package com.vo.movie.forecast.parser.feign.online.cinema

import com.vo.movie.forecast.parser.api.online.cinema.OnlineCinemaApi
import com.vo.movie.forecast.parser.api.online.cinema.dto.MovieAccessInfoDTO
import com.vo.movie.forecast.parser.api.online.cinema.dto.MovieInfoDTO
import com.vo.movie.forecast.parser.api.online.cinema.dto.OnlineCinema
import feign.Param
import feign.RequestLine

interface OnlineCinemaApiFeign : OnlineCinemaApi {

    @RequestLine("POST /online-cinemas/{cinemaName}/search")
    override fun getMovieAccessInfo(@Param("cinemaName") onlineCinemaName: OnlineCinema, movieInfo: MovieInfoDTO): MovieAccessInfoDTO
}