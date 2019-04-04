package com.vo.movie.forecast.bot.handler.callback

import com.vo.movie.forecast.backend.storage.api.LocalityApi
import com.vo.movie.forecast.bot.util.*
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class LocalityFirstLetterCallbackHandler(private val localityApi: LocalityApi) : CallbackUpdateHandler(Callback.LOCALITY_FIRST_LETTER) {

    override fun handle(update: Update) {
        val firstLetter = update.getCallbackData().single()
        val localitiesNames = call({ localityApi.getLocalitiesNamesByLetter(firstLetter) }, update.chatId())
        val keyboard = localitiesNames.map {
            val button = createInlineKeyboardButton(it, Callback.LOCALITY_NAME.addCallbackPrefix(it))
            createRowButton(button)
        }
        val inlineKeyboardMarkup = createInlineKeyboardMarkup(keyboard)
        val editMessageText = update.createEditMessageText("Выберите ваш населённый пункт", inlineKeyboardMarkup)
        getBot().execute(editMessageText)
    }
}