package com.vo.movie.forecast.backend.core.rest

import com.vo.movie.forecast.backend.core.service.UserService
import com.vo.movie.forecast.commons.dto.Locality
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {

    @PutMapping("/{userId}/registerMovie")
    fun registerMovie(@PathVariable userId: Long, @RequestParam kinopoiskMovieId: Long) {
        userService.registerMovie(userId, kinopoiskMovieId)
    }

    @PutMapping("/{userId}/updateLocality")
    fun updateLocality(@PathVariable userId: Long, @RequestBody locality: Locality) {
        userService.updateLocality(userId, locality)
    }
}