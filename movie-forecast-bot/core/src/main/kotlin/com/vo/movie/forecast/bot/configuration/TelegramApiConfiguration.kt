package com.vo.movie.forecast.bot.configuration

import com.vo.movie.forecast.bot.MovieForecastBot
import com.vo.movie.forecast.bot.handler.UpdateHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.telegram.telegrambots.meta.TelegramBotsApi


@Configuration
open class TelegramApiConfiguration(private val botProperties: BotProperties,
                                    private var handlers: List<UpdateHandler>) {

    @Bean
    open fun movieForecastBotHandler(): MovieForecastBot {
        val movieForecastBotHandler = MovieForecastBot(botProperties)
        handlers.forEach { movieForecastBotHandler.addHandler(it) }
        return movieForecastBotHandler
    }

    @Bean
    open fun telegramBotsApi(movieForecastBotHandler: MovieForecastBot): TelegramBotsApi {
        val telegramBotsApi = TelegramBotsApi()
        telegramBotsApi.registerBot(movieForecastBotHandler)
        return telegramBotsApi
    }
}