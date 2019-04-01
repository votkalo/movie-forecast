package com.vo.movie.forecast.bot.handler.callback

import com.vo.movie.forecast.backend.api.bot.MovieApi
import com.vo.movie.forecast.bot.util.*
import com.vo.movie.forecast.commons.data.MovieInfo
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class MovieFirstLetterCallbackHandler(private val movieApi: MovieApi) : CallbackUpdateHandler(Callback.MOVIE_FIRST_LETTER) {

    override fun handle(update: Update) {
        val firstLetter = update.getCallbackData().single()
        val movies = call({ movieApi.getMoviesByLetter(update.userId(), firstLetter) }, update.chatId())
        val moviesInfoStrings = movies.map { it.createCallbackButtonInfo() }
        val keyboard = moviesInfoStrings.map {
            val button = createInlineKeyboardButton(it.text, Callback.MOVIE_INFO.addCallbackPrefix(it.callbackData))
            createRowButton(button)
        }
        val inlineKeyboardMarkup = createInlineKeyboardMarkup(keyboard)
        val editMessageText = update.createEditMessageText("Выберите отслеживаемый фильм", inlineKeyboardMarkup)
        getBot().execute(editMessageText)
    }

    private fun MovieInfo.createCallbackButtonInfo() = CallbackButtonInfo(getCallbackButtonText(), getCallbackData())

    private fun MovieInfo.getCallbackButtonText(): String {
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

    private fun MovieInfo.getCallbackData(): String {
        val allowCallbackDataByteLength = 64

        val callbackData = createCallbackData(title, year)
        val callbackDataWithPrefixLength = getCallback().addCallbackPrefix(callbackData).toByteArray().size

        if (callbackDataWithPrefixLength <= allowCallbackDataByteLength) {
            return callbackData
        }

        val extraTitleLength = callbackDataWithPrefixLength - allowCallbackDataByteLength
        val notFullTitle = title.toByteArray().dropLast(extraTitleLength).toByteArray()
        return createCallbackData(String(notFullTitle), year)
    }

    //TODO: Combine methods. Add 3 argument: template
    private fun createCallbackButtonText(title: String, year: String?): String {
        val messageBuilder = StringBuilder()
        messageBuilder.append(title)
        if (year != null) {
            messageBuilder.append(" ($year)")
        }
        return messageBuilder.toString()
    }

    private fun createCallbackData(title: String, year: String?): String {
        val messageBuilder = StringBuilder()
        messageBuilder.append(title)
        if (year != null) {
            messageBuilder.append("∫$year")
        }
        return messageBuilder.toString()
    }

    private data class CallbackButtonInfo(val text: String, val callbackData: String)
}