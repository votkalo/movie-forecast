package com.vo.movie.page.reader.api

import com.vo.movie.page.reader.dto.PageRequestExternal
import com.vo.movie.page.reader.dto.PageResponseExternal
import feign.RequestLine

interface PageReaderApiExternalFeign : PageReaderApiExternal {

    @RequestLine("POST /pages")
    override fun getPage(pageRequest: PageRequestExternal): PageResponseExternal

}