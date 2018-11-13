package com.vo.movie.page.reader.api

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.springframework.stereotype.Component

@Component
class PageReaderApiClient() : PageReaderApi {

    override fun getPageContent(url: String): Document = Jsoup.connect(url).get()

}