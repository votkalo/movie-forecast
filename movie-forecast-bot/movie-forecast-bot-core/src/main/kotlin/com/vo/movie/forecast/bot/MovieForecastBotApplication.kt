package com.vo.movie.forecast.bot

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan
import org.telegram.telegrambots.ApiContextInitializer

@SpringBootApplication
@ComponentScan("com.vo.movie")
open class MovieForecastBotApplication

fun main(args: Array<String>) {
    ApiContextInitializer.init()
    SpringApplication.run(MovieForecastBotApplication::class.java, *args)
}