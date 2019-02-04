package com.vo.movie.forecast.bot.handler.callback

import com.vo.movie.forecast.backend.api.api.UserApi
import com.vo.movie.forecast.bot.util.createMessage
import com.vo.movie.forecast.parser.provider.LocalityProvider
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class LocalityNameCallbackHandler(private val localityProvider: LocalityProvider,
                                  private val userApi: UserApi) : CallbackUpdateHandler(Callback.LOCALITY_NAME) {

    override fun handle(update: Update) {
        val localityName = update.getCallbackData()
        userApi.updateLocality(update.userId(), localityProvider.getLocalityByName(localityName))
        val message = createMessage(update.chatId(), "Ваш текущий населённый пункт обновлён на:\n<b>$localityName</b>")
        getBot().execute(message)
    }
}