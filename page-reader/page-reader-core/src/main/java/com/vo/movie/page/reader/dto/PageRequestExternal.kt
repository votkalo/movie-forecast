package com.vo.movie.page.reader.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class PageRequestExternal(@JsonProperty("url") val url: String,
                               @JsonProperty("infinite") val infinite: Boolean = false)