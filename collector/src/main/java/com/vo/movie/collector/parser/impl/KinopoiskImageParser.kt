package com.vo.movie.collector.parser.impl

import com.vo.movie.collector.configuration.ParserProperties
import com.vo.movie.collector.parser.ImageParser
import com.vo.movie.collector.util.createFullURL
import com.vo.movie.collector.util.parseValues
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.springframework.stereotype.Service

@Service
class KinopoiskImageParser(parserProperties: ParserProperties) : ImageParser {

    private val kinopoisk: ParserProperties.Resource
    private val poster: ParserProperties.Resource.Element

    init {
        kinopoisk = parserProperties.kinopoisk!!
        poster = kinopoisk.movie?.poster!!
    }

    override fun getImageURL(document: Document): String {
        return document.parseMovieImageURL()
    }

    private fun Document.parseMovieImageURL(): String {
        select(poster.selector)
            .forEach {
                return kinopoisk.createFullURL(poster.parseValues(it.attr(poster.attribute))[0])
            }
        return ""
    }
}