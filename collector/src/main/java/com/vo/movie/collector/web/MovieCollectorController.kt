package com.vo.movie.collector.web

import com.vo.movie.collector.service.MovieCollector
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/collector")
class MovieCollectorController(private val movieCollector: MovieCollector) {

    @PostMapping("/movies/collect")
    fun collectMovies() {
        movieCollector.collectMovies()
    }
}