package com.vo.movie.forecast.backend.core

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan("com.vo.movie")
open class MovieForecastBackendApplication

fun main(args: Array<String>) {
    SpringApplication.run(MovieForecastBackendApplication::class.java, *args)
}

