package com.vo.movie.page.reader.service

import com.vo.movie.page.reader.dto.PageRequest
import com.vo.movie.page.reader.dto.PageResponse

interface PageReaderService {

    fun getPage(pageRequest: PageRequest): PageResponse
}