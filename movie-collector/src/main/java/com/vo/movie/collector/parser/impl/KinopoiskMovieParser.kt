package com.vo.movie.collector.parser.impl

import com.vo.movie.collector.configuration.ParserProperties
import com.vo.movie.collector.dto.Movie
import com.vo.movie.collector.parser.MovieParser
import com.vo.movie.collector.util.MovieDictionary
import com.vo.movie.collector.util.createByKinopoiskProperties
import com.vo.movie.collector.util.removeLines
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.springframework.stereotype.Service

@Service
class KinopoiskMovieParser(parserProperties: ParserProperties) : MovieParser {

    private val kinopoisk: ParserProperties.Resource
    private val premiereLink: ParserProperties.Resource.PremieresProperties.PremieresElement
    private val movie: ParserProperties.Resource.MovieProperties
    private val title: ParserProperties.Resource.Element
    private val property: ParserProperties.Resource.MovieProperties.MovieElement
    private val ratingKinopoisk: ParserProperties.Resource.Element
    private val ratingIMDB: ParserProperties.Resource.Element

    init {
        kinopoisk = parserProperties.kinopoisk!!
        premiereLink = kinopoisk.premieres?.premiereLink!!
        movie = kinopoisk.movie!!
        title = movie.title!!
        property = movie.property!!
        ratingKinopoisk = movie.ratingKinopoisk!!
        ratingIMDB = movie.ratingIMDB!!
    }

    override fun getPremiereURLs(document: Document): List<String> =
        document
            .select(premiereLink.selector)
            .map { kinopoisk.url?.toASCIIString() + it.attr(premiereLink.attribute) }

    override fun getMovie(document: Document): Movie {
        val movieProperties: MutableMap<String, List<String>> = HashMap()
        movieProperties.putAll(document.parseMovieTitles())
        movieProperties.putAll(document.parseMovieProperties())
        movieProperties.putAll(document.parseMovieRatings())
        //TODO: Parse image
        return createByKinopoiskProperties(movieProperties)
    }

    private fun Document.parseMovieTitles(): Map<String, List<String>> {
        val movieTitles: MutableMap<String, List<String>> = HashMap()
        select(title.selector)
            .forEach {
                movieTitles[MovieDictionary.RUSSIAN_TITLE.kinopoisk] = arrayListOf(it.getMovieRussianTitleValue())
                movieTitles[MovieDictionary.ORIGINAL_TITLE.kinopoisk] = arrayListOf(it.getMovieOriginalTitleValue())
            }
        return movieTitles
    }

    private fun Document.parseMovieProperties(): Map<String, List<String>> {
        val movieProperties: MutableMap<String, List<String>> = HashMap()
        select(property.selector)
            .forEach { movieProperties[it.getMoviePropertyKey()] = it.getMoviePropertyValues() }
        return movieProperties
    }

    private fun Document.parseMovieRatings(): Map<String, List<String>> {
        val movieRatings: MutableMap<String, List<String>> = HashMap()
        select(ratingKinopoisk.selector)
            .forEach { movieRatings[MovieDictionary.RATING_KINOPOISK.kinopoisk] = arrayListOf(it.getMovieRatingKinopoiskValues()) }
        select(ratingIMDB.selector)
            .forEach { movieRatings[MovieDictionary.RATING_IMDB.kinopoisk] = arrayListOf(it.getMovieRatingIMDBValues()) }
        return movieRatings
    }


    private fun Element.getMovieRussianTitleValue(): String = this.child(0).text()

    private fun Element.getMovieOriginalTitleValue(): String = this.child(1).text() ?: getMovieRussianTitleValue()


    private fun Element.getMoviePropertyKey(): String = this.child(0).text()

    private fun Element.getMoviePropertyValues(): List<String> = parsePropertyValues(this.child(1).text())


    private fun Element.getMovieRatingKinopoiskValues(): String = this.text()

    private fun Element.getMovieRatingIMDBValues(): String = parseRatingIMDBValue(this.child(1).text())


    private fun parsePropertyValues(line: String): List<String> {
        return line
            .removeLines(property.unusedLines)
            .trim()
            .split(property.delimiter!!)
            .filter { it.isNotBlank() }
    }

    private fun parseRatingIMDBValue(line: String): String {
        return line.subSequence(6..9).toString()
    }
}
