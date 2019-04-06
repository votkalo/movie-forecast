package com.vo.movie.forecast.backend.user.core.rest

import com.vo.movie.forecast.backend.user.core.service.MovieService
import com.vo.movie.forecast.parser.dto.movie.MovieDTO
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users/{userId}/movies")
class MovieController(private val movieService: MovieService) {

    @PostMapping
    fun registerMovie(@PathVariable userId: Long, @RequestBody movie: MovieDTO) =
        movieService.registerMovie(userId, movie)

    @GetMapping("/{kinopoiskMovieId}/exists")
    fun existsMovie(@PathVariable userId: Long, @PathVariable kinopoiskMovieId: Long): Boolean =
        movieService.existsMovie(userId, kinopoiskMovieId)

    @GetMapping
    fun getMovies(@PathVariable userId: Long, @RequestParam page: Int, @RequestParam size: Int): List<MovieDTO> =
        movieService.getMovies(userId, page, size)

    @GetMapping("/letters")
    fun getMoviesLetters(@PathVariable userId: Long): List<String> = movieService.getMoviesLetters(userId)

    @GetMapping("/letters/{letter}")
    fun getMoviesByLetter(@PathVariable userId: Long, @PathVariable letter: Char): List<MovieDTO> =
        movieService.getMoviesByLetter(userId, letter)

    @DeleteMapping("/{kinopoiskMovieId}")
    fun deleteMovie(@PathVariable userId: Long, @PathVariable kinopoiskMovieId: Long) =
        movieService.deleteMovie(userId, kinopoiskMovieId)
}