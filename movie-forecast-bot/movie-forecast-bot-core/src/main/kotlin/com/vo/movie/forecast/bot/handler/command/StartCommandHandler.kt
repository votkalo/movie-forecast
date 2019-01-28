package com.vo.movie.forecast.bot.handler.command

import com.vo.movie.forecast.backend.api.api.UserApi
import com.vo.movie.forecast.backend.api.dto.User
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class StartCommandHandler(private val userApi: UserApi) : CommandUpdateHandler(Command.START) {

    override fun handle(update: Update) {
        if (update.chat().isUserChat) {
            //TODO UserId equals ChatID
            userApi.registerUser(User(update.chatId(), update.userId()))
        }
    }
}