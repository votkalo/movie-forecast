package com.vo.movie.commons.dto

data class Movie(val title: String) {

    var year: Int? = null

    lateinit var county: String
}