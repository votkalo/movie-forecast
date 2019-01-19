package com.vo.movie.forecast.bot.handler.command

import com.vo.movie.forecast.bot.handler.UpdateHandler
import org.telegram.telegrambots.meta.api.objects.Update

abstract class CommandUpdateHandler(private val command: Command) : UpdateHandler() {

    override fun shouldHandle(update: Update): Boolean =
            update.hasMessage() && update.message.isCommand && command.value == update.message.text
}