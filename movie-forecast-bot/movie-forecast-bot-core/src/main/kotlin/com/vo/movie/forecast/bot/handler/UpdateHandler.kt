package com.vo.movie.forecast.bot.handler

import com.vo.movie.forecast.bot.util.createEditMessageText
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup

abstract class UpdateHandler {
    private lateinit var bot: TelegramLongPollingBot

    fun setBot(bot: TelegramLongPollingBot) {
        this.bot = bot
    }

    fun getBot(): TelegramLongPollingBot = bot

    protected fun Update.chatId(): Long {
        if (hasMessage()) {
            return message.chatId
        }
        if (hasCallbackQuery() && callbackQuery.message != null) {
            return callbackQuery.message.chatId
        }
        if (hasMessage()) {
            return message.from.id.toLong()
        }
        if (hasCallbackQuery()) {
            return callbackQuery.from.id.toLong()
        }
        if (hasInlineQuery()) {
            return inlineQuery.from.id.toLong()
        }
        throw NullPointerException("Chat id not found")
    }

    protected fun Update.userId(): Long {
        if (hasMessage()) {
            return message.from.id.toLong()
        }
        if (hasCallbackQuery()) {
            return callbackQuery.from.id.toLong()
        }
        if (hasInlineQuery()) {
            return inlineQuery.from.id.toLong()
        }
        throw NullPointerException("User id not found")
    }

    protected fun Update.createEditMessageText(text: String): EditMessageText {
        if (hasMessage()) {
            return createEditMessageText(message.chatId, message.messageId, text)
        }
        if (hasCallbackQuery() && callbackQuery.message != null) {
            return createEditMessageText(callbackQuery.message.chatId, callbackQuery.message.messageId, text)
        }
        if (hasCallbackQuery()) {
            return createEditMessageText(callbackQuery.inlineMessageId, text)
        }
        throw NullPointerException("Data for edit message not found")
    }

    protected fun Update.createEditMessageText(text: String, inlineKeyboardMarkup: InlineKeyboardMarkup): EditMessageText {
        val editMessageText = createEditMessageText(text)
        editMessageText.replyMarkup = inlineKeyboardMarkup
        return editMessageText
    }

    abstract fun handle(update: Update)

    abstract fun shouldHandle(update: Update): Boolean
}