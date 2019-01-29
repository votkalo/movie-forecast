package com.vo.movie.forecast.backend.core.rest

import com.vo.movie.forecast.backend.api.dto.Follow
import com.vo.movie.forecast.backend.core.service.FollowService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/follows")
class FollowController(private val followService: FollowService) {

    @PostMapping
    fun registerFollow(@RequestBody follow: Follow): Follow {
        return followService.registerFollow(follow)
    }
}