package com.vo.movie.page.reader.service.impl

import com.vo.movie.page.reader.api.PageReaderApiExternal
import com.vo.movie.page.reader.dto.PageRequest
import com.vo.movie.page.reader.dto.PageRequestExternal
import com.vo.movie.page.reader.dto.PageResponse
import com.vo.movie.page.reader.dto.PageResponseExternal
import com.vo.movie.page.reader.service.PageReaderService
import com.vo.movie.page.reader.util.toExternal
import com.vo.movie.page.reader.util.toResponse
import org.springframework.stereotype.Service

@Service
class PageReaderServiceImpl(private val pageReaderApiExternal: PageReaderApiExternal) : PageReaderService {

    override fun getPage(pageRequest: PageRequest): PageResponse {
        val pageRequestExternal: PageRequestExternal = pageRequest.toExternal()
        val pageResponseExternal: PageResponseExternal = pageReaderApiExternal.getPage(pageRequestExternal)
        return pageResponseExternal.toResponse()
    }
}