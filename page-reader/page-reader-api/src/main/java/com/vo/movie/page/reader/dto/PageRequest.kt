package com.vo.movie.page.reader.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class PageRequest(@JsonProperty("url") val url: String,
                       @JsonProperty("infinite") val infinite: Boolean = false)