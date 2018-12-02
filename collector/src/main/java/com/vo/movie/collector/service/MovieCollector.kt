package com.vo.movie.collector.service

import java.time.LocalDate

interface MovieCollector {

    fun collectMovies(month: Int = LocalDate.now().monthValue, year: Int = LocalDate.now().year)
}