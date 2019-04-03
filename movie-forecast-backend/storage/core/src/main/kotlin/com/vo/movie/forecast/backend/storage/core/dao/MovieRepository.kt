package com.vo.movie.forecast.backend.storage.core.dao

import com.vo.movie.forecast.backend.storage.core.document.Movie

interface MovieRepository {

    fun saveMovie(movie: Movie)
}