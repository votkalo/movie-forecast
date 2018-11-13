package com.vo.movie.collector.link

import org.jsoup.nodes.Document
import java.time.LocalDate

interface MoviePageLinksCollector {

    fun getLinks(month: Int = LocalDate.now().monthValue, year: Int = LocalDate.now().year): List<String>
}