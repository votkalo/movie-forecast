package com.vo.movie.forecast.backend.core.rest

import com.vo.movie.forecast.backend.core.service.UserService
import com.vo.movie.forecast.backend.data.UserInfo
import com.vo.movie.forecast.commons.data.Locality
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {

    @PutMapping("/{userId}/updateLocality")
    fun updateLocality(@PathVariable userId: Long, @RequestBody locality: Locality) = userService.updateLocality(userId, locality)

    @GetMapping
    fun getUsersInfoWithLocality(@RequestParam page: Int, @RequestParam size: Int): List<UserInfo> = userService.getUsersInfoWithLocality(page, size)

    @GetMapping("/ids")
    fun getUsersIds(@RequestParam page: Int, @RequestParam size: Int): List<Long> = userService.getUsersIds(page, size)
}