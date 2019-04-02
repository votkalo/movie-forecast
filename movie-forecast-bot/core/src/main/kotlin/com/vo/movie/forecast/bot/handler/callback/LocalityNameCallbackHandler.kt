package com.vo.movie.forecast.bot.handler.callback

import com.vo.movie.forecast.backend.user.api.bot.UserApi
import com.vo.movie.forecast.bot.util.call
import com.vo.movie.forecast.parser.provider.locality.LocalityProvider
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class LocalityNameCallbackHandler(private val localityProvider: LocalityProvider,
                                  private val userApi: UserApi) : CallbackUpdateHandler(Callback.LOCALITY_NAME) {

    override fun handle(update: Update) {
        val localityName = update.getCallbackData()
        call({ userApi.updateLocality(update.userId(), localityProvider.getLocalityByName(localityName)) }, update.chatId())
        val editMessageText = update.createEditMessageText("Ваш текущий населённый пункт обновлён на:\n<b>$localityName</b>")
        getBot().execute(editMessageText)
    }
}