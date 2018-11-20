package com.vo.movie.page.reader.feign

import com.vo.movie.page.reader.api.PageReaderApi
import com.vo.movie.page.reader.dto.PageRequest
import com.vo.movie.page.reader.dto.PageResponse
import feign.RequestLine

interface PageReaderApiFeign : PageReaderApi {

    @RequestLine("POST /pages")
    override fun getPage(pageRequest: PageRequest): PageResponse
}