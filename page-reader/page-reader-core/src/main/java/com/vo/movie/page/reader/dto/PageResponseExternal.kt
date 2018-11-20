package com.vo.movie.page.reader.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class PageResponseExternal(@JsonProperty("html") val html: String)