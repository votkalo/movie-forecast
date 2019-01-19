package com.vo.movie.forecast.bot.handler.answer

import com.vo.movie.forecast.bot.handler.UpdateHandler
import com.vo.movie.forecast.bot.handler.command.Command
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class CityCallbackQueryHandler : UpdateHandler() {

    companion object {
        const val CITY_CALLBACK_QUERY_MESSAGE = "Выберите ваш населённый пункт"
    }

    override fun shouldHandle(update: Update): Boolean = update.hasCallbackQuery() &&
            update.callbackQuery.message.text == CITY_CALLBACK_QUERY_MESSAGE

    override fun handle(update: Update) {
        if (update.callbackQuery.data == Command.LOCALITY.value) {
            return
        }
        val message = SendMessage()
        message.chatId = update.callbackQuery.message.chatId.toString()
        message.text = "Ваш текущий город ${update.callbackQuery.data}"
        getBot().execute(message)
    }
}