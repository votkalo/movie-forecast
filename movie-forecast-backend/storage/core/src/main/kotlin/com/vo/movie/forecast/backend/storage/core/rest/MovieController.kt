package com.vo.movie.forecast.backend.storage.core.rest

import com.vo.movie.forecast.backend.storage.core.service.MovieService
import com.vo.movie.forecast.parser.dto.movie.MovieDTO
import com.vo.movie.forecast.parser.dto.movie.MovieSearchParamsDTO
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/movies")
class MovieController(private val movieService: MovieService) {

    @PostMapping("/search")
    fun searchMovie(@RequestBody searchParams: MovieSearchParamsDTO): List<MovieDTO> =
        movieService.searchMovie(searchParams)

    @GetMapping("/{kinopoiskMovieId}")
    fun getMovie(@PathVariable kinopoiskMovieId: Long): MovieDTO = movieService.getMovie(kinopoiskMovieId)
}