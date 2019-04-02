package com.vo.movie.forecast.backend.user.core

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan("com.vo.movie")
open class MovieForecastBackendUserApplication

fun main(args: Array<String>) {
    SpringApplication.run(MovieForecastBackendUserApplication::class.java, *args)
}

