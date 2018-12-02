package com.vo.movie.collector.parser.impl

import com.vo.movie.collector.configuration.ParserProperties
import com.vo.movie.collector.configuration.StorageProperties
import com.vo.movie.collector.dto.Movie
import com.vo.movie.collector.parser.MovieParser
import com.vo.movie.collector.util.MovieDictionary
import com.vo.movie.collector.util.createByKinopoiskProperties
import com.vo.movie.collector.util.createFullURL
import com.vo.movie.collector.util.parseValues
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.springframework.stereotype.Service
import java.awt.image.BufferedImage
import java.io.File
import java.net.URL
import java.nio.file.Paths
import javax.imageio.ImageIO
import java.nio.file.Files



@Service
class KinopoiskMovieParser(parserProperties: ParserProperties) : MovieParser {

    private val kinopoisk: ParserProperties.Resource
    private val premiereLink: ParserProperties.Resource.Element
    private val movie: ParserProperties.Resource.MovieProperties
    private val title: ParserProperties.Resource.Element
    private val property: ParserProperties.Resource.Element
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
            .map { kinopoisk.createFullURL(it.attr(premiereLink.attribute)) }

    override fun getMovie(document: Document): Movie {
        val movieProperties: MutableMap<String, List<String>> = HashMap()
        movieProperties.putAll(document.parseMovieTitles())
        movieProperties.putAll(document.parseMovieProperties())
        movieProperties.putAll(document.parseMovieRatings())
        movieProperties.putAll(document.parseMovieSourceURL())
        return createByKinopoiskProperties(movieProperties)
    }

    private fun Document.parseMovieTitles(): Map<String, List<String>> {
        val values: MutableMap<String, List<String>> = HashMap()
        select(title.selector)
            .forEach {
                values[MovieDictionary.RUSSIAN_TITLE.kinopoisk] = arrayListOf(it.getMovieRussianTitleValue())
                values[MovieDictionary.ORIGINAL_TITLE.kinopoisk] = arrayListOf(it.getMovieOriginalTitleValue())
            }
        return values
    }

    private fun Document.parseMovieProperties(): Map<String, List<String>> {
        val values: MutableMap<String, List<String>> = HashMap()
        select(property.selector)
            .forEach { values[it.getMoviePropertyKey()] = it.getMoviePropertyValues() }
        return values
    }

    private fun Document.parseMovieRatings(): Map<String, List<String>> {
        val values: MutableMap<String, List<String>> = HashMap()
        select(ratingKinopoisk.selector)
            .forEach {
                val ratingValue = it.getMovieRatingKinopoiskValue().orEmpty()
                if (ratingValue.isNotBlank()) values[MovieDictionary.RATING_KINOPOISK.kinopoisk] = arrayListOf(ratingValue)

            }
        select(ratingIMDB.selector)
            .forEach {
                val ratingValue = it.getMovieRatingIMDBValue().orEmpty()
                if (ratingValue.isNotBlank()) values[MovieDictionary.RATING_IMDB.kinopoisk] = arrayListOf(ratingValue)
            }
        return values
    }

    private fun Document.parseMovieSourceURL(): Map<String, List<String>> {
        val values: MutableMap<String, List<String>> = HashMap()
        values[MovieDictionary.SOURCE_URL.kinopoisk] = arrayListOf(baseUri())
        return values
    }

    private fun Element.getMovieRussianTitleValue(): String = this.child(0).text()

    private fun Element.getMovieOriginalTitleValue(): String = this.child(1).text() ?: getMovieRussianTitleValue()


    private fun Element.getMoviePropertyKey(): String = this.child(0).text()

    private fun Element.getMoviePropertyValues(): List<String> = property.parseValues(this.child(1).text())


    private fun Element.getMovieRatingKinopoiskValue(): String? = ratingKinopoisk.parseValues(this.text()).getOrNull(0)

    private fun Element.getMovieRatingIMDBValue(): String? =
        ratingIMDB.parseValues(getElementsContainingOwnText(MovieDictionary.RATING_IMDB.kinopoisk).text()).getOrNull(1)
}
