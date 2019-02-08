package com.vo.movie.forecast.worker

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan("com.vo.movie")
open class MovieForecastWorkerApplication

fun main(args: Array<String>) {
    SpringApplication.run(MovieForecastWorkerApplication::class.java, *args)
}

