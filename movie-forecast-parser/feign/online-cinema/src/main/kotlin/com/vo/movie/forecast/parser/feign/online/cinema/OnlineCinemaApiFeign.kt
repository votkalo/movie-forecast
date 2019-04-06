package com.vo.movie.forecast.parser.feign.online.cinema

import com.vo.movie.forecast.parser.api.online.cinema.OnlineCinemaApi
import com.vo.movie.forecast.parser.dto.online.cinema.MovieAccessInfoDTO
import com.vo.movie.forecast.parser.dto.online.cinema.MovieInfoDTO
import com.vo.movie.forecast.parser.dto.online.cinema.OnlineCinema
import feign.Param
import feign.RequestLine

interface OnlineCinemaApiFeign : OnlineCinemaApi {

    @RequestLine("POST /online-cinemas/{cinemaName}/search")
    override fun getMovieAccessInfo(@Param("cinemaName") onlineCinemaName: OnlineCinema, movieInfo: MovieInfoDTO): MovieAccessInfoDTO
}