package com.vo.movie.forecast.bot.handler.callback

import com.vo.movie.forecast.bot.util.*
import com.vo.movie.forecast.parser.provider.LocalityProvider
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class LocalityFirstLetterCallbackHandler(private val localityProvider: LocalityProvider) : CallbackUpdateHandler(Callback.LOCALITY_FIRST_LETTER) {

    override fun handle(update: Update) {
        val firstLetter = update.getCallbackData().single()
        val localitiesNames = localityProvider.getLocalitiesNamesByLetter(firstLetter)
        val keyboard = localitiesNames.map {
            val button = createInlineKeyboardButton(it, Callback.LOCALITY_NAME.addCallbackPrefix(it))
            createRowButton(button)
        }
        val inlineKeyboardMarkup = createInlineKeyboardMarkup(keyboard)
        val message = createMessage(
                update.chatId(),
                "Выберите ваш населённый пункт",
                inlineKeyboardMarkup
        )
        getBot().execute(message)
    }
}