package com.vo.movie.collector

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class MovieCollectorApplication

fun main(args: Array<String>) {
    SpringApplication.run(MovieCollectorApplication::class.java, *args)
}

