package com.vo.movie.forecast.bot.handler

import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.objects.Update

abstract class UpdateHandler {
    private lateinit var bot: TelegramLongPollingBot

    fun setBot(bot: TelegramLongPollingBot) {
        this.bot = bot
    }

    protected fun getBot(): TelegramLongPollingBot = bot

    fun Update.chatId(): Long {
        if (hasMessage()) {
            return message.chatId
        }
        if (hasCallbackQuery()) {
            return callbackQuery.message.chatId
        }
        throw NullPointerException("Chat id not found")
    }

    abstract fun handle(update: Update)

    abstract fun shouldHandle(update: Update): Boolean
}