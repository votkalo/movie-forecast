package com.vo.movie.collector.service

import com.vo.movie.collector.dto.Image

interface ImageCollector {

    fun collectImage(url: String): Image?
}