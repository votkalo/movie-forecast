package com.vo.movie.forecast.bot.message.movie

import com.vo.movie.forecast.bot.message.commons.MessageCreator
import com.vo.movie.forecast.parser.dto.movie.MovieDTO
import org.springframework.stereotype.Component

@Component
class MovieBotMessageCreator : MessageCreator(), MovieMessageCreator {

    override fun createFullMovieInfo(movie: MovieDTO): String {
        return movie.descriptionTemplate()
    }

    override fun createMovieTitle(movie: MovieDTO): String {
        return movie.titleTemplate()
    }

    override fun createMovieTitle(title: String, year: String?): String {
        return titleTemplate(title, year)
    }

    override fun createMovieTitleForButton(movie: MovieDTO): String {
        val allowTextLength = 38
        val threeDotsLength = 3

        val callbackButtonText = movie.titleTemplate()
        if (callbackButtonText.length <= allowTextLength) {
            return callbackButtonText
        }

        val extraTitleLength = callbackButtonText.length + threeDotsLength - allowTextLength
        val notFullTitle = threeDots(movie.title.dropLast(extraTitleLength))
        return titleTemplate(notFullTitle, movie.year)
    }

    override fun createShortMovieDescription(movie: MovieDTO): String {
        return movie.shortDescriptionTemplate()
    }

    private fun MovieDTO.descriptionTemplate(): String = """
            <b>${titleTemplate()}</b>
            ${valueOrEmpty(originalTitle, "<i>$originalTitle</i>")}
            <a href="$bigPosterURL">&#8205;</a>
            <a href="$sourceURL">Кинопоиск</a> ${valueOrEmpty(kinopoiskRating, "&#x2B50; $kinopoiskRating")}
            ${valueOrEmpty(genres, "Жанр: $genres")}
            ${valueOrEmpty(countries, "Страна: $countries")}
        """.trimIndent()

    private fun MovieDTO.titleTemplate(): String = titleTemplate(title, year)

    private fun titleTemplate(title: String, year: String?): String = "$title ${valueOrEmpty(year, "($year)")}"

    private fun MovieDTO.shortDescriptionTemplate(): String = """
            ${valueOrEmpty(originalTitle)} ${valueOrEmpty(kinopoiskRating, "★$kinopoiskRating")}
            ${valueOrEmpty(genres)}
            ${valueOrEmpty(countries)}
        """.trimIndent()

    private fun threeDots(value: String) = "$value..."

}