package com.vo.movie.forecast.backend.core.rest

import com.vo.movie.forecast.backend.core.service.MovieService
import com.vo.movie.forecast.commons.data.Movie
import com.vo.movie.forecast.commons.data.MovieInfo
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users/{userId}/movies")
class MovieController(private val movieService: MovieService) {

    @PostMapping
    fun registerMovie(@PathVariable userId: Long, @RequestBody movie: Movie) = movieService.registerMovie(userId, movie)

    @GetMapping("/{kinopoiskMovieId}/exists")
    fun existsMovie(@PathVariable userId: Long, @PathVariable kinopoiskMovieId: Long): Boolean = movieService.existsMovie(userId, kinopoiskMovieId)

    @GetMapping
    fun getMovies(@PathVariable userId: Long, @RequestParam page: Int, @RequestParam size: Int): List<MovieInfo> = movieService.getMovies(userId, page, size)

    @GetMapping("/letters")
    fun getMoviesLetters(@PathVariable userId: Long): List<String> = movieService.getMoviesLetters(userId)

    @GetMapping("/letters/{letter}")
    fun getMoviesByLetter(@PathVariable userId: Long, @PathVariable letter: Char): List<MovieInfo> = movieService.getMoviesByLetter(userId, letter)

    @PostMapping("/search")
    fun searchMovie(@PathVariable userId: Long, @RequestBody movieInfo: MovieInfo): Movie = movieService.searchMovie(userId, movieInfo)

    @DeleteMapping("/{kinopoiskMovieId}")
    fun deleteMovie(@PathVariable userId: Long, @PathVariable kinopoiskMovieId: Long) = movieService.deleteMovie(userId, kinopoiskMovieId)
}