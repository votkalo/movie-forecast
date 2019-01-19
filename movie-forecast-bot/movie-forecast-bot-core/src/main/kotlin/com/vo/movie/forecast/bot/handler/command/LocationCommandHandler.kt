package com.vo.movie.forecast.bot.handler.command

import org.springframework.stereotype.Component
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow
import java.util.*

@Component
class LocationCommandHandler : CommandUpdateHandler(Command.LOCATION) {

    override fun handle(update: Update) {
        val locationButton = createLocationKeyboardButton()
        val message = createMessageAndSingleButtonKeyboard(
                update.message.chatId.toString(),
                locationButton,
                "Укажите ваше местоположение"
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
        replyKeyboardMarkup.keyboard = Collections.singletonList(createSingleButtonKeyboardRow(button))
        return replyKeyboardMarkup
    }

    private fun createSingleButtonKeyboardRow(button: KeyboardButton): KeyboardRow {
        val keyboardRow = KeyboardRow()
        keyboardRow.add(button)
        return keyboardRow
    }

    private fun createMessageAndSingleButtonKeyboard(chatId: String, button: KeyboardButton, text: String): SendMessage {
        val message = SendMessage()
        message.chatId = chatId
        message.text = text
        message.enableMarkdown(true)
        message.replyMarkup = createSingleButtonReplyKeyboardMarkup(button)
        return message
    }
}