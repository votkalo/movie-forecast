package com.vo.movie.collector.service.impl

import com.vo.movie.collector.configuration.ParserProperties
import com.vo.movie.collector.parser.MovieParser
import com.vo.movie.collector.service.MovieCollector
import com.vo.movie.page.reader.api.PageReaderApi
import com.vo.movie.page.reader.dto.PageRequest
import com.vo.movie.page.reader.dto.PageResponse
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.springframework.stereotype.Service

@Service
class KinopoiskMovieCollector(private val pageReaderApi: PageReaderApi,
                              private val movieParser: MovieParser,
                              parserProperties: ParserProperties) : MovieCollector {

    private val kinopoisk: ParserProperties.Resource
    private val premieres: ParserProperties.Resource.Properties

    init {
        kinopoisk = parserProperties.kinopoisk!!
        premieres = kinopoisk.premieres!!
    }

    override fun collectMovies(month: Int, year: Int) {
        val premieresUrl: String = createPremieresUrl(month, year)
        val premieresPageResponse: PageResponse = pageReaderApi.getPage(PageRequest(premieresUrl, true))
        val premieresDocument: Document = Jsoup.parse(premieresPageResponse.html)
        val premiereURLs: List<String> = movieParser.getPremiereURLs(premieresDocument)
        premiereURLs.forEach {
            val premierePageResponse: PageResponse = pageReaderApi.getPage(PageRequest(it))
            val premiereDocument: Document = Jsoup.parse(premierePageResponse.html)
            //TODO: parse premiere
        }
    }

    private fun createPremieresUrl(month: Int, year: Int): String = String.format(premieres.urlTemplate!!, year, month)
}