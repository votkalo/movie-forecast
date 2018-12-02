package com.vo.movie.collector.parser

import org.jsoup.nodes.Document

interface ImageParser {

    fun getImageURL(document: Document): String
}