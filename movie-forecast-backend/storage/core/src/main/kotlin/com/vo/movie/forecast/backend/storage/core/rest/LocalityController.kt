package com.vo.movie.forecast.backend.storage.core.rest

import com.vo.movie.forecast.backend.storage.core.service.LocalityService
import com.vo.movie.forecast.backend.storage.data.LocalityDTO
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/localities")
class LocalityController(private val localityService: LocalityService) {

    @GetMapping
    fun getLocalities(): List<LocalityDTO> = localityService.getLocalities()

    @GetMapping("/letters")
    fun getLocalitiesLetters(): List<String> = localityService.getLocalitiesLetters()

    @GetMapping("/names/{letter}")
    fun getLocalitiesNamesByLetter(@PathVariable letter: Char): List<String> = localityService.getLocalitiesNamesByLetter(letter)

    @GetMapping("/{name}")
    fun getLocalityByName(@PathVariable name: String): LocalityDTO = localityService.getLocalityByName(name)
}