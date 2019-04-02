package com.vo.movie.forecast.backend.user.core.rest

import com.vo.movie.forecast.backend.user.core.service.UserService
import com.vo.movie.forecast.backend.user.data.UserInfo
import com.vo.movie.forecast.commons.data.Locality
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {

    @PutMapping("/{userId}/locality")
    fun updateLocality(@PathVariable userId: Long, @RequestBody locality: Locality) = userService.updateLocality(userId, locality)

    @DeleteMapping("/{userId}/locality")
    fun removeLocality(@PathVariable userId: Long) = userService.removeLocality(userId)

    @GetMapping
    fun getUsersInfoWithLocality(@RequestParam page: Int, @RequestParam size: Int): List<UserInfo> = userService.getUsersInfoWithLocality(page, size)

    @GetMapping("/ids")
    fun getUsersIds(@RequestParam page: Int, @RequestParam size: Int): List<Long> = userService.getUsersIds(page, size)
}