package com.vo.movie.collector.parser

import org.jsoup.nodes.Document

interface MovieParser {

    fun getPremiereURLs(document: Document): List<String>
}