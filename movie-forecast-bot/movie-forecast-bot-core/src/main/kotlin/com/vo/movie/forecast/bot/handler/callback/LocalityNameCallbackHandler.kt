package com.vo.movie.forecast.bot.handler.callback

import com.vo.movie.forecast.bot.util.createMessage
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class LocalityNameCallbackHandler : CallbackUpdateHandler(Callback.LOCALITY_NAME) {

    override fun handle(update: Update) {
        val localityName = update.getCallbackData()
        val message = createMessage(update.chatId(), "Ваш текущий населённый пункт обновлён на:\n<b>$localityName</b>")
        getBot().execute(message)
    }
}