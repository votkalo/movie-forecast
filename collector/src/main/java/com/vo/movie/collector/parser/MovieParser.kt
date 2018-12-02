package com.vo.movie.collector.parser

import com.vo.movie.collector.dto.Movie
import org.jsoup.nodes.Document

interface MovieParser {

    fun getPremiereURLs(document: Document): List<String>

    fun getMovie(document: Document): Movie
}