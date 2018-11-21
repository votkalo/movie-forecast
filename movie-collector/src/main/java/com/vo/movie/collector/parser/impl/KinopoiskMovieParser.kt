package com.vo.movie.collector.parser.impl

import com.vo.movie.collector.configuration.ParserProperties
import com.vo.movie.collector.parser.MovieParser
import org.jsoup.nodes.Document
import org.springframework.stereotype.Service

@Service
class KinopoiskMovieParser(parserProperties: ParserProperties) : MovieParser {

    private val kinopoisk: ParserProperties.Resource
    private val premieres: ParserProperties.Resource.Properties

    init {
        kinopoisk = parserProperties.kinopoisk!!
        premieres = kinopoisk.premieres!!
    }

    override fun getPremiereURLs(document: Document): List<String> =
        document
            .select(premieres.selector)
            .map { kinopoisk.url?.toASCIIString() + it.attr(premieres.attribute) }

}