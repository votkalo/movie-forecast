package com.vo.movie.forecast.bot.handler.callback

import com.vo.movie.forecast.backend.storage.api.MovieApi
import com.vo.movie.forecast.bot.util.call
import com.vo.movie.forecast.bot.util.createEditMessageText
import com.vo.movie.forecast.bot.util.createMessage
import com.vo.movie.forecast.parser.dto.movie.MovieDTO
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class MovieDescriptionCallbackHandler(private val movieApi: MovieApi) : CallbackUpdateHandler(Callback.MOVIE_DESCRIPTION) {

    override fun handle(update: Update) {
        val kinopoiskMovieId = update.getCallbackData()
        val movie = call({ movieApi.getMovie(kinopoiskMovieId.toLong()) }, update.chatId())
        getBot().execute(update.createEditMessageText(movie.createMessageText()))
    }

    private fun MovieDTO.createMessageText(): String {
        val messageBuilder = StringBuilder()
        messageBuilder.append("<b>")
        messageBuilder.append(title)
        if (year != null) {
            messageBuilder.append(" ($year)")
        }
        messageBuilder.append("</b>")
        if (originalTitle != null) {
            messageBuilder.append("\n<i>$originalTitle</i>")
        }
        messageBuilder.append("\n<a href=\"$bigPosterURL\">&#8205;</a>")
        messageBuilder.append("\n<a href=\"$sourceURL\">Кинопоиск</a>")
        if (kinopoiskRating != null) {
            messageBuilder.append(" &#x2B50; $kinopoiskRating")
        }
        if (genres != null) {
            messageBuilder.append("\nЖанр: $genres")
        }
        if (countries != null) {
            messageBuilder.append("\nСтрана: $countries")
        }
        return messageBuilder.toString()
    }
}