package com.vo.movie.collector.service.impl

import com.vo.movie.collector.configuration.ParserProperties
import com.vo.movie.collector.dto.Movie
import com.vo.movie.collector.entity.MovieEntity
import com.vo.movie.collector.mapper.MovieMapper
import com.vo.movie.collector.parser.MovieParser
import com.vo.movie.collector.repository.MovieRepository
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
                              private val movieMapper: MovieMapper,
                              private val movieRepository: MovieRepository,
                              parserProperties: ParserProperties) : MovieCollector {

    private val kinopoisk: ParserProperties.Resource = parserProperties.kinopoisk!!

    override fun collectMovies(month: Int, year: Int) {
        val premieresURL: String = createPremieresUrl(month, year)
        val premiereURLs: List<String> = getPremiereURLs(premieresURL)
        val movies: List<Movie> = collectMovies(premiereURLs)
        val movieEntities: List<MovieEntity> = movies.map { movieMapper.mapToMovieEntity(it) }
        movieRepository.saveAll(movieEntities)
    }

    private fun createPremieresUrl(month: Int, year: Int): String =
        String.format(kinopoisk.premieres?.urlTemplate!!, year, month)

    private fun getPremiereURLs(premieresURL: String): List<String> {
        val premieresPageResponse: PageResponse = pageReaderApi.getPage(PageRequest(premieresURL, true))
        val premieresDocument: Document = Jsoup.parse(premieresPageResponse.html)
        return movieParser.getPremiereURLs(premieresDocument)
    }

    private fun collectMovies(premiereURLs: List<String>): List<Movie> {
        return premiereURLs.map {
            val premierePageResponse: PageResponse = pageReaderApi.getPage(PageRequest(it))
            val premiereDocument: Document = Jsoup.parse(premierePageResponse.html, it)
            movieParser.getMovie(premiereDocument)
        }
    }
}