package com.vo.movie.page.reader.util

import com.vo.movie.page.reader.dto.PageRequest
import com.vo.movie.page.reader.dto.PageRequestExternal
import com.vo.movie.page.reader.dto.PageResponse
import com.vo.movie.page.reader.dto.PageResponseExternal

fun PageRequest.toExternal() = PageRequestExternal(url, infinite)

fun PageResponseExternal.toResponse() = PageResponse(html)
