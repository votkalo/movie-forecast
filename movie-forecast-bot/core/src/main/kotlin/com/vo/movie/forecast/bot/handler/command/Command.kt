package com.vo.movie.forecast.bot.handler.command

enum class Command(val value: String) {
    LOCALITY("/locality"),
    LOCATION("/location"),
    UNSUBSCRIBE("/unsubscribe"),
    LEAVE("/leave");
}