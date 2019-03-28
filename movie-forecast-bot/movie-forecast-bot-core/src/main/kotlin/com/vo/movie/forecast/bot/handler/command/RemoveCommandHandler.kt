package com.vo.movie.forecast.bot.handler.command

import com.vo.movie.forecast.backend.api.bot.MovieApi
import com.vo.movie.forecast.bot.handler.callback.Callback
import com.vo.movie.forecast.bot.util.addCallbackPrefix
import com.vo.movie.forecast.bot.util.createInlineKeyboardButton
import com.vo.movie.forecast.bot.util.createInlineKeyboardMarkup
import com.vo.movie.forecast.bot.util.createMessage
import org.apache.commons.collections4.ListUtils
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class RemoveCommandHandler(private val movieApi: MovieApi) : CommandUpdateHandler(Command.REMOVE) {

    override fun handle(update: Update) {
        //TODO: обёртка в стиле котлина через блок кода, на каждый метод API, при возникновении ошибки выкидывать алерт
        val letters = movieApi.getMoviesLetters(update.userId())

        if (letters.isEmpty()) {
            val message = createMessage(update.chatId(), "У вас нет отслеживаемых фильмов")
            getBot().execute(message)
            return
        }

        val buttons = letters.map {
            createInlineKeyboardButton(it, Callback.MOVIE_FIRST_LETTER.addCallbackPrefix(it))
        }
        val keyboard = ListUtils.partition(buttons, 4)
        val inlineKeyboardMarkup = createInlineKeyboardMarkup(keyboard)
        val message = createMessage(
                update.chatId(),
                "Выберите букву, с которой начинается отслеживаемый фильм",
                inlineKeyboardMarkup
        )
        getBot().execute(message)
    }
}