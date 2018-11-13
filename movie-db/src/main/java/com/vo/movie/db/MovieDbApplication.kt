package com.vo.movie.db

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class MovieDbApplication

fun main(args: Array<String>) {
    SpringApplication.run(MovieDbApplication::class.java, *args)
}
