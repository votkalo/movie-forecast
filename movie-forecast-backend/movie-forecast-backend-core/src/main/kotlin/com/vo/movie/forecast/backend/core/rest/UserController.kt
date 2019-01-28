package com.vo.movie.forecast.backend.core.rest

import com.vo.movie.forecast.backend.api.dto.User
import com.vo.movie.forecast.backend.core.service.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {

    @PostMapping
    fun registerUser(@RequestBody user: User): User {
        return userService.registerUser(user)
    }

    @GetMapping("/{userId}/exists")
    fun isUserExist(@PathVariable userId: Long): Boolean {
        return userService.isUserExist(userId)
    }
}