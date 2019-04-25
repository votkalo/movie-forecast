package com.vo.movie.forecast.bot.handler.callback

import com.vo.movie.forecast.backend.user.api.UserMovieApi
import com.vo.movie.forecast.bot.message.movie.MovieMessageCreator
import com.vo.movie.forecast.bot.util.*
import com.vo.movie.forecast.parser.dto.movie.MovieDTO
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class MovieFirstLetterCallbackHandler(private val userMovieApi: UserMovieApi,
                                      private val movieMessageCreator: MovieMessageCreator) : CallbackUpdateHandler(Callback.MOVIE_FIRST_LETTER) {

    override fun handle(update: Update) {
        val firstLetter = update.getCallbackData().single()
        val movies = call({ userMovieApi.getMoviesByLetter(update.userId(), firstLetter) }, update.chatId())
        val moviesInfoStrings = movies.map { it.createCallbackButtonInfo() }
        val keyboard = moviesInfoStrings.map {
            val button = createInlineKeyboardButton(it.text, Callback.MOVIE.addCallbackPrefix(it.callbackData))
            createRowButton(button)
        }
        val inlineKeyboardMarkup = createInlineKeyboardMarkup(keyboard)
        val editMessageText = update.createEditMessageText("Выберите отслеживаемый фильм", inlineKeyboardMarkup)
        getBot().execute(editMessageText)
    }

    private fun MovieDTO.createCallbackButtonInfo() =
        CallbackButtonInfo(movieMessageCreator.createMovieTitleForButton(this), kinopoiskMovieId.toString())

    private data class CallbackButtonInfo(val text: String, val callbackData: String)
}