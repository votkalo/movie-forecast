package com.vo.movie.collector.parser

import com.vo.movie.collector.dto.Movie
import org.jsoup.nodes.Document

interface MovieParser {

    fun parse(documents: List<Document>): List<Movie>
}