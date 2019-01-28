package com.vo.movie.forecast.bot.handler

import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.objects.Chat
import org.telegram.telegrambots.meta.api.objects.Update

abstract class UpdateHandler {
    private lateinit var bot: TelegramLongPollingBot

    fun setBot(bot: TelegramLongPollingBot) {
        this.bot = bot
    }

    protected fun getBot(): TelegramLongPollingBot = bot

    protected fun Update.chatId(): Long {
        if (hasMessage()) {
            return message.chatId
        }
        if (hasCallbackQuery() && callbackQuery.message != null) {
            return callbackQuery.message.chatId
        }
        throw NullPointerException("Chat id not found")
    }

    protected fun Update.chat(): Chat {
        if (hasMessage()) {
            return message.chat
        }
        if (hasCallbackQuery() && callbackQuery.message != null) {
            return callbackQuery.message.chat
        }
        throw NullPointerException("Chat not found")
    }

    protected fun Update.userId(): Long {
        if (hasMessage()) {
            return message.from.id.toLong()
        }
        if (hasCallbackQuery()) {
            return callbackQuery.from.id.toLong()
        }
        throw NullPointerException("UserId not found")
    }

    abstract fun handle(update: Update)

    abstract fun shouldHandle(update: Update): Boolean
}