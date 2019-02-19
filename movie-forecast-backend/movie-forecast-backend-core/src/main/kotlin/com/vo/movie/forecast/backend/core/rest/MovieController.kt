package com.vo.movie.forecast.backend.core.rest

import com.vo.movie.forecast.backend.core.service.MovieService
import com.vo.movie.forecast.backend.data.MovieInfo
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users/{userId}/movies")
class MovieController(private val movieService: MovieService) {

    @GetMapping
    fun getUserMovies(@PathVariable userId: Long, @RequestParam page: Int, @RequestParam size: Int): List<MovieInfo> = movieService.getUserMovies(userId, page, size)
}