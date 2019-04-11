package com.vo.movie.forecast.backend.storage.core.dao

import com.vo.movie.forecast.backend.storage.core.document.MovieAccess
import com.vo.movie.forecast.backend.storage.core.document.MovieInfo
import com.vo.movie.forecast.backend.storage.core.document.OnlineCinema

interface OnlineCinemaRepository {

    fun getMovieAccess(onlineCinema: OnlineCinema, movieInfo: MovieInfo): MovieAccess?

    fun save(onlineCinema: OnlineCinema, movieAccess: MovieAccess)
}