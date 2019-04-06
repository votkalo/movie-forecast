package com.vo.movie.forecast.bot.handler.callback

import com.vo.movie.forecast.backend.storage.api.LocalityApi
import com.vo.movie.forecast.backend.user.api.UserApi
import com.vo.movie.forecast.bot.util.call
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class LocalityNameCallbackHandler(private val localityApi: LocalityApi,
                                  private val userApi: UserApi) : CallbackUpdateHandler(Callback.LOCALITY_NAME) {

    override fun handle(update: Update) {
        val localityName = update.getCallbackData()
        call(
                { userApi.updateLocality(update.userId(), localityApi.getLocalityByName(localityName)) },
                update.chatId()
        )
        val editMessageText =
            update.createEditMessageText("Ваш текущий населённый пункт обновлён на:\n<b>$localityName</b>")
        getBot().execute(editMessageText)
    }
}