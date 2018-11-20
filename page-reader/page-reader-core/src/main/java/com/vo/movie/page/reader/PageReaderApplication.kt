package com.vo.movie.page.reader

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan("com.vo.movie")
open class PageReaderApplication

fun main(args: Array<String>) {
    SpringApplication.run(PageReaderApplication::class.java, *args)
}

