package com.vo.movie.page.reader.api

import com.vo.movie.page.reader.dto.PageRequest
import com.vo.movie.page.reader.dto.PageResponse

interface PageReaderApi {

    fun getPage(pageRequest: PageRequest): PageResponse

}