package com.vo.movie.forecast.bot.handler.callback

import com.vo.movie.forecast.backend.storage.api.MovieApi
import com.vo.movie.forecast.bot.util.*
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class MovieCallbackHandler(private val movieApi: MovieApi) : CallbackUpdateHandler(Callback.MOVIE) {

    override fun handle(update: Update) {
        val kinopoiskMovieId = update.getCallbackData()
        val movie = call({ movieApi.getMovie(kinopoiskMovieId.toLong()) }, update.chatId())
        val keyboard = arrayListOf(
                createRowButton(
                        createInlineKeyboardButton(
                                "Посмотреть описание",
                                Callback.MOVIE_DESCRIPTION.addCallbackPrefix(kinopoiskMovieId)
                        )
                ),
                createRowButton(
                        createInlineKeyboardButton(
                                "Убрать из отслеживаемых",
                                Callback.MOVIE_UNSUBSCRIBE.addCallbackPrefix(kinopoiskMovieId)
                        )
                )
        )

        val editMessageText =
            update.createEditMessageText(
                    createMovieInfo(movie.title, movie.year),
                    createInlineKeyboardMarkup(keyboard)
            )
        getBot().execute(editMessageText)
    }

    private fun createMovieInfo(title: String, year: String?): String {
        val messageBuilder = StringBuilder()
        messageBuilder.append(title)
        if (year != null) {
            messageBuilder.append(" ($year)")
        }
        return messageBuilder.toString()
    }
}