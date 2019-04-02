package com.vo.movie.forecast.bot.handler.command

import com.vo.movie.forecast.backend.user.api.bot.UserApi
import com.vo.movie.forecast.bot.util.call
import com.vo.movie.forecast.bot.util.createMessage
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class LeaveCommandHandler(private val userApi: UserApi) : CommandUpdateHandler(Command.LEAVE) {

    override fun handle(update: Update) {
        call({ userApi.removeLocality(update.userId()) }, update.chatId())
        val message = createMessage(
                update.chatId(),
                "Информация о населённом пункте удалена"
        )
        getBot().execute(message)
    }
}