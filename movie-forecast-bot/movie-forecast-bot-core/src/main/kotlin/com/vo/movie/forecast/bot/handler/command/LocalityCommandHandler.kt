package com.vo.movie.forecast.bot.handler.command

import com.vo.movie.forecast.bot.handler.callback.Callback
import com.vo.movie.forecast.bot.util.addCallbackPrefix
import com.vo.movie.forecast.bot.util.createInlineKeyboardButton
import com.vo.movie.forecast.bot.util.createInlineKeyboardMarkup
import com.vo.movie.forecast.bot.util.createMessage
import com.vo.movie.forecast.parser.provider.LocalityProvider
import org.apache.commons.collections4.ListUtils
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Update


@Component
class LocalityCommandHandler(private val localityProvider: LocalityProvider) : CommandUpdateHandler(Command.LOCALITY) {

    override fun handle(update: Update) {
        val letters = localityProvider.getLocalitiesLetters()
        val buttons = letters.map {
            createInlineKeyboardButton(it, Callback.LOCALITY_FIRST_LETTER.addCallbackPrefix(it))
        }
        val keyboard = ListUtils.partition(buttons, 4)
        val inlineKeyboardMarkup = createInlineKeyboardMarkup(keyboard)
        val message = createMessage(
                update.chatId(),
                "Выберите букву, с которой начинается ваш населённый пункт",
                inlineKeyboardMarkup
        )
        getBot().execute(message)
    }
}