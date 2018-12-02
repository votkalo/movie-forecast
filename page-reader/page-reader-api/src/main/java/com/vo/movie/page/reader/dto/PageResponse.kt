package com.vo.movie.page.reader.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class PageResponse(@JsonProperty("html") val html: String)