package com.vo.movie.forecast.bot

import com.vo.movie.forecast.bot.configuration.BotProperties

class MovieForecastBot(private val botProperties: BotProperties) : BotWithHandlers() {

    override fun getBotUsername(): String {
        return botProperties.username!!
    }

    override fun getBotToken(): String {
        return botProperties.token!!
    }
}