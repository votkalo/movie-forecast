package com.vo.movie.forecast.bot.handler.callback

import com.vo.movie.forecast.backend.storage.data.MovieDTO
import com.vo.movie.forecast.backend.user.api.bot.UserMovieApi
import com.vo.movie.forecast.bot.util.*
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class MovieFirstLetterCallbackHandler(private val userMovieApi: UserMovieApi) :
        CallbackUpdateHandler(Callback.MOVIE_FIRST_LETTER) {

    override fun handle(update: Update) {
        val firstLetter = update.getCallbackData().single()
        val movies = call({ userMovieApi.getMoviesByLetter(update.userId(), firstLetter) }, update.chatId())
        val moviesInfoStrings = movies.map { it.createCallbackButtonInfo() }
        val keyboard = moviesInfoStrings.map {
            val button = createInlineKeyboardButton(it.text, Callback.MOVIE_INFO.addCallbackPrefix(it.callbackData))
            createRowButton(button)
        }
        val inlineKeyboardMarkup = createInlineKeyboardMarkup(keyboard)
        val editMessageText = update.createEditMessageText("Выберите отслеживаемый фильм", inlineKeyboardMarkup)
        getBot().execute(editMessageText)
    }

    private fun MovieDTO.createCallbackButtonInfo() = CallbackButtonInfo(getCallbackButtonText(), kinopoiskMovieId.toString())

    private fun MovieDTO.getCallbackButtonText(): String {
        val allowTextLength = 38
        val threeDots = "..."

        val callbackButtonText = createCallbackButtonText(title, year)

        if (callbackButtonText.length <= allowTextLength) {
            return callbackButtonText
        }

        val extraTitleLength = callbackButtonText.length + threeDots.length - allowTextLength
        val notFullTitle = title.dropLast(extraTitleLength) + threeDots
        return createCallbackButtonText(notFullTitle, year)
    }

    private fun createCallbackButtonText(title: String, year: String?): String {
        val messageBuilder = StringBuilder()
        messageBuilder.append(title)
        if (year != null) {
            messageBuilder.append(" ($year)")
        }
        return messageBuilder.toString()
    }

    private data class CallbackButtonInfo(val text: String, val callbackData: String)
}