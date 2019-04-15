package com.vo.movie.forecast.bot.handler.callback

enum class Callback(val value: String) {
    LOCALITY_FIRST_LETTER("LL:"),
    LOCALITY_NAME("LN:"),
    MOVIE_ID("MI:"),
    MOVIE_FIRST_LETTER("ML:"),
    MOVIE("M:"),
    MOVIE_UNSUBSCRIBE("MU:"),
    MOVIE_DESCRIPTION("MD:");
}