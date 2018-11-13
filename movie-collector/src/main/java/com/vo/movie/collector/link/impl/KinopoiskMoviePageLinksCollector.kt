package com.vo.movie.collector.link.impl

import com.vo.movie.collector.link.MoviePageLinksCollector
import com.vo.movie.page.reader.api.PageReaderApi
import org.springframework.stereotype.Service

@Service
class KinopoiskMoviePageLinksCollector(private val pageReaderApi: PageReaderApi) : MoviePageLinksCollector {

    private val urlPrefix: String = "https://www.kinopoisk.ru"
    private val urlTemplate: String = "$urlPrefix/premiere/ru/%d/month/%d/"
    private fun createUrl(month: Int, year: Int): String = String.format(urlTemplate, year, month)

    private val cssElementSelector: String = "div.premier_item > div.text > div.textBlock > span.name > a"
    private val cssElementAttribute: String = "href"

    override fun getLinks(month: Int, year: Int): List<String> {
        val url: String = createUrl(month, year)
        return getAllLinks(url)
    }

    private fun getAllLinks(url: String): List<String> {
        val document = pageReaderApi.getPageContent(url)
        return document.select(cssElementSelector).map { urlPrefix + it.attr(cssElementAttribute) }
    }
}