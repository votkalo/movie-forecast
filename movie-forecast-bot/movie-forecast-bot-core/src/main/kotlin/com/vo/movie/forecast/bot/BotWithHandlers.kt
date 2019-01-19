package com.vo.movie.forecast.bot

import com.vo.movie.forecast.bot.handler.UpdateHandler
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.objects.Update

abstract class BotWithHandlers : TelegramLongPollingBot() {

    private val handlers: MutableList<UpdateHandler> = ArrayList()

    override fun onUpdateReceived(update: Update) {
        handlers.filter { it.shouldHandle(update) }.forEach { it.handle(update) }
    }

    fun addHandler(updateHandler: UpdateHandler) {
        updateHandler.setBot(this)
        handlers.add(updateHandler)
    }
}