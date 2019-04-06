package com.vo.movie.forecast.bot.handler.command

import com.vo.movie.forecast.backend.user.api.UserMovieApi
import com.vo.movie.forecast.bot.handler.callback.Callback
import com.vo.movie.forecast.bot.util.*
import org.apache.commons.collections4.ListUtils
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class UnsubscribeCommandHandler(private val userMovieApi: UserMovieApi) : CommandUpdateHandler(Command.UNSUBSCRIBE) {

    override fun handle(update: Update) {
        val letters = call({ userMovieApi.getMoviesLetters(update.userId()) }, update.chatId())

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