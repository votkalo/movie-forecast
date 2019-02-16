package com.vo.movie.forecast.notifier

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan("com.vo.movie")
open class MovieForecastNotifierApplication

fun main(args: Array<String>) {
    SpringApplication.run(MovieForecastNotifierApplication::class.java, *args)
}

