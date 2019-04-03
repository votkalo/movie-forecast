package com.vo.movie.forecast.bot.util

import com.vo.movie.forecast.bot.handler.command.Command
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow
import java.util.*

fun createMessage(chatId: Long, text: String): SendMessage {
    val message = SendMessage()
    message.setChatId(chatId)
    message.text = text
    message.enableHtml(true)
    return message
}

fun createMessage(chatId: Long, text: String, replyMarkup: ReplyKeyboard): SendMessage {
    val message = createMessage(chatId, text)
    message.replyMarkup = replyMarkup
    return message
}

fun createEditMessageText(chatId: Long, messageId: Int, text: String): EditMessageText {
    val editMessageText = EditMessageText()
    editMessageText.setChatId(chatId)
    editMessageText.messageId = messageId
    editMessageText.text = text
    editMessageText.enableHtml(true)
    return editMessageText
}

fun createEditMessageText(chatId: Long,
                          messageId: Int,
                          text: String,
                          inlineKeyboardMarkup: InlineKeyboardMarkup): EditMessageText {
    val editMessageText = createEditMessageText(chatId, messageId, text)
    editMessageText.replyMarkup = inlineKeyboardMarkup
    return editMessageText
}

fun createEditMessageText(inlineMessageId: String, text: String): EditMessageText {
    val editMessageText = EditMessageText()
    editMessageText.inlineMessageId = inlineMessageId
    editMessageText.text = text
    editMessageText.enableHtml(true)
    return editMessageText
}

fun createEditMessageText(inlineMessageId: String,
                          text: String,
                          inlineKeyboardMarkup: InlineKeyboardMarkup): EditMessageText {
    val editMessageText = createEditMessageText(inlineMessageId, text)
    editMessageText.replyMarkup = inlineKeyboardMarkup
    return editMessageText
}

fun createRowButton(button: InlineKeyboardButton): List<InlineKeyboardButton> = Collections.singletonList(button)

fun createRowButton(button: KeyboardButton): KeyboardRow {
    val keyboardRow = KeyboardRow()
    keyboardRow.add(button)
    return keyboardRow
}

fun createOneRowButton(button: InlineKeyboardButton): List<List<InlineKeyboardButton>> =
    Collections.singletonList(createRowButton(button))

fun createOneRowButton(button: KeyboardButton): List<KeyboardRow> = Collections.singletonList(createRowButton(button))

fun createInlineKeyboardButton(text: String, callbackData: String): InlineKeyboardButton {
    val inlineKeyboardButton = InlineKeyboardButton()
    inlineKeyboardButton.text = text
    inlineKeyboardButton.callbackData = callbackData
    return inlineKeyboardButton
}

fun createInlineKeyboardMarkup(keyboard: List<List<InlineKeyboardButton>>): InlineKeyboardMarkup {
    val inlineKeyboardMarkup = InlineKeyboardMarkup()
    inlineKeyboardMarkup.keyboard = keyboard
    return inlineKeyboardMarkup
}

fun createSearchLocalityInlineKeyboardMarkup(): InlineKeyboardMarkup {
    val localitySearchButton = createInlineKeyboardButton("Выбрать населённый пункт", Command.LOCALITY.value)
    return createInlineKeyboardMarkup(createOneRowButton(localitySearchButton))
}