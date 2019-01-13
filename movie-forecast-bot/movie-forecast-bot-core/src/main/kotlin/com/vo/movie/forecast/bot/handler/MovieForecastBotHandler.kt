package com.vo.movie.forecast.bot.handler

import com.vo.movie.forecast.bot.configuration.BotProperties
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update

class MovieForecastBotHandler(private val botProperties: BotProperties) : TelegramLongPollingBot() {
    override fun getBotUsername(): String {
        return botProperties.username!!
    }

    override fun getBotToken(): String {
        return botProperties.token!!
    }

    override fun onUpdateReceived(update: Update?) {
        val message = SendMessage()
        message.text = update?.message?.from?.firstName + " привет"
        message.chatId = update?.message?.chatId.toString()
        execute(message)
    }
}