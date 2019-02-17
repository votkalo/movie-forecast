package com.vo.movie.forecast.backend.core.rest

import com.vo.movie.forecast.backend.core.service.UserService
import com.vo.movie.forecast.backend.data.User
import com.vo.movie.forecast.commons.data.Locality
import com.vo.movie.forecast.commons.data.Movie
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {

    @PutMapping("/{userId}/registerMovie")
    fun registerMovie(@PathVariable userId: Long, @RequestBody movie: Movie) = userService.registerMovie(userId, movie)

    @PutMapping("/{userId}/updateLocality")
    fun updateLocality(@PathVariable userId: Long, @RequestBody locality: Locality) = userService.updateLocality(userId, locality)

    @GetMapping("/{userId}/movies/{kinopoiskMovieId}/exists")
    fun existsMovie(@PathVariable userId: Long, @PathVariable kinopoiskMovieId: Long): Boolean = userService.existsMovie(userId, kinopoiskMovieId)

    @GetMapping
    fun getUsers(@RequestParam page: Int, @RequestParam size: Int): List<User> = userService.getUsers(page, size)
}