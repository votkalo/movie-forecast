package com.vo.movie.forecast.bot.handler.command

import com.vo.movie.forecast.bot.util.createMessage
import com.vo.movie.forecast.bot.util.createOneRowButton
import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton

@Component
class LocationCommandHandler : CommandUpdateHandler(Command.LOCATION) {

    override fun handle(update: Update) {
        val locationButton = createLocationKeyboardButton()
        val message =
            createMessage(
                    update.chatId(),
                    "Укажите ваше местоположение",
                    createSingleButtonReplyKeyboardMarkup(locationButton)
            )
        getBot().execute(message)
    }

    private fun createLocationKeyboardButton(): KeyboardButton {
        val keyboardButton = KeyboardButton()
        keyboardButton.requestLocation = true
        keyboardButton.text = "Отправить геопозицию"
        return keyboardButton
    }

    private fun createSingleButtonReplyKeyboardMarkup(button: KeyboardButton): ReplyKeyboardMarkup {
        val replyKeyboardMarkup = ReplyKeyboardMarkup()
        replyKeyboardMarkup.oneTimeKeyboard = true
        replyKeyboardMarkup.resizeKeyboard = true
        replyKeyboardMarkup.keyboard = createOneRowButton(button)
        return replyKeyboardMarkup
    }
}