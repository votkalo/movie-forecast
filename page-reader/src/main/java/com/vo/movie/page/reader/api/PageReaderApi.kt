package com.vo.movie.page.reader.api

import org.jsoup.nodes.Document

interface PageReaderApi {

    fun getPageContent(url: String): Document;
}