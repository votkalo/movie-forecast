package com.vo.movie.forecast.backend.storage.core.rest

import com.vo.movie.forecast.backend.storage.core.service.MovieService
import com.vo.movie.forecast.backend.storage.data.MovieDTO
import com.vo.movie.forecast.backend.storage.data.MovieSearchParamsDTO
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/movies")
class MovieController(private val movieService: MovieService) {

    @PostMapping("/search")
    fun searchMovie(@RequestBody searchParams: MovieSearchParamsDTO): List<MovieDTO> =
        movieService.searchMovie(searchParams)
}