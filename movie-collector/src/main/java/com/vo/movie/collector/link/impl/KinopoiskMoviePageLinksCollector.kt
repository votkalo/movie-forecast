package com.vo.movie.collector.link.impl

import com.vo.movie.collector.link.MoviePageLinksCollector
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import org.springframework.stereotype.Service

@Service
class KinopoiskMoviePageLinksCollector : MoviePageLinksCollector {

    private val urlPrefix: String = "https://www.kinopoisk.ru"
    private val urlTemplate: String = "$urlPrefix/premiere/ru/%d/month/%d/"
    private fun createUrl(urlTemplate: String, month: Int, year: Int): String = String.format(urlTemplate, year, month)

    private val cssElementSelector: String = "div.premier_item > div.text > div.textBlock > span.name > a"
    private val cssElementAttribute: String = "href"

    override fun getLinks(month: Int, year: Int): List<String> {
        val documents: MutableList<String> = ArrayList()
        val url: String = createUrl(urlTemplate, month, year)
        return getAllLinks(url, documents)
    }

    private fun getAllLinks(url: String, links: MutableList<String>, page: Int = 0): List<String> {
        var currentPage = page
        val document = getPremiersPage(url, currentPage++)
        if (document.hasText()) {
            val elements: Elements = document.select(cssElementSelector)
            elements.forEach { links.add(urlPrefix + it.attr(cssElementAttribute)) }
            return getAllLinks(url, links, currentPage)
        }
        return links
    }

    private fun getPremiersPage(url: String, page: Int): Document {
        return Jsoup
            .connect(url)
            .data(
                hashMapOf(
                    Pair("page", page.toString()),
                    Pair("ajax", "true"))
            )
            .post()
    }

}

fun main(args: Array<String>) {
    val f = KinopoiskMoviePageLinksCollector()
    f.getLinks()
}