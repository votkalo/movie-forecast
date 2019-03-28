package com.vo.movie.forecast.bot.handler.callback

enum class Callback(val value: String) {
    LOCALITY_FIRST_LETTER("LL:"),
    LOCALITY_NAME("LN:"),
    MOVIE_ID("MID:"),
    MOVIE_FIRST_LETTER("ML:"),
    MOVIE_INFO("MI:");
}