package com.vo.movie.page.reader.web

import com.vo.movie.page.reader.dto.PageRequest
import com.vo.movie.page.reader.dto.PageResponse
import com.vo.movie.page.reader.service.PageReaderService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/page-reader")
class PageReaderController(private val pageReaderService: PageReaderService) {

    @PostMapping("/pages")
    fun getPage(@RequestBody pageRequest: PageRequest): PageResponse {
        return pageReaderService.getPage(pageRequest)
    }
}