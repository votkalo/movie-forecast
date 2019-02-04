package com.vo.movie.forecast.backend.core.rest

import com.vo.movie.forecast.backend.core.service.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {

    @PostMapping("/{userId}/registerMovie")
    fun registerMovie(@PathVariable userId: Long, @RequestParam kinopoiskMovieId: Long) {
        userService.registerMovie(userId, kinopoiskMovieId)
    }
}