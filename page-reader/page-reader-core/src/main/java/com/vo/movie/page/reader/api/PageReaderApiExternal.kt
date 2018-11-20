package com.vo.movie.page.reader.api

import com.vo.movie.page.reader.dto.PageRequestExternal
import com.vo.movie.page.reader.dto.PageResponseExternal

interface PageReaderApiExternal {

    fun getPage(pageRequest: PageRequestExternal): PageResponseExternal
}