package com.vo.movie.collector.dto

data class Movie(val title: String) {

    var year: Int? = null

    lateinit var county: String
}