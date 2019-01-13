package com.vo.movie.forecast.bot.configuration

import com.vo.movie.forecast.bot.handler.MovieForecastBotHandler
import com.vo.movie.forecast.parser.api.MovieForecastParserApi
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.telegram.telegrambots.meta.TelegramBotsApi

@Configuration
@EnableConfigurationProperties(BotProperties::class)
open class TelegramApiConfiguration(private val botProperties: BotProperties,
                                    private val movieForecastParserApi: MovieForecastParserApi) {

    @Bean
    open fun movieForecastBotHandler(): MovieForecastBotHandler = MovieForecastBotHandler(botProperties, movieForecastParserApi)

    @Bean
    open fun telegramBotsApi(movieForecastBotHandler: MovieForecastBotHandler): TelegramBotsApi {
        val telegramBotsApi = TelegramBotsApi()
        telegramBotsApi.registerBot(movieForecastBotHandler)
        return telegramBotsApi
    }
}