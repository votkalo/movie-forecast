package com.vo.movie.forecast.backend.storage.core

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan("com.vo.movie")
open class MovieForecastBackendStorageApplication

fun main(args: Array<String>) {
    SpringApplication.run(MovieForecastBackendStorageApplication::class.java, *args)
}

