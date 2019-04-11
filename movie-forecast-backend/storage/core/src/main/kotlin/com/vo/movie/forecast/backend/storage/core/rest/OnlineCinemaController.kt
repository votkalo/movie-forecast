package com.vo.movie.forecast.backend.storage.core.rest

import com.vo.movie.forecast.backend.storage.core.service.OnlineCinemaService
import com.vo.movie.forecast.parser.dto.online.cinema.MovieAccessDTO
import com.vo.movie.forecast.parser.dto.online.cinema.MovieInfoDTO
import com.vo.movie.forecast.parser.dto.online.cinema.OnlineCinemaDTO
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/online-cinemas")
class OnlineCinemaController(private val onlineCinemaService: OnlineCinemaService) {

    @PostMapping("/{onlineCinema}/search")
    fun getMovieAccess(@PathVariable onlineCinema: OnlineCinemaDTO, @RequestBody movieInfo: MovieInfoDTO): MovieAccessDTO =
        onlineCinemaService.getMovieAccess(onlineCinema, movieInfo)
}