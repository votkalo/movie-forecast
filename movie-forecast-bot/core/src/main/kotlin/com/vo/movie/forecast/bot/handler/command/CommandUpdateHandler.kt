package com.vo.movie.forecast.bot.handler.command

import com.vo.movie.forecast.bot.handler.UpdateHandler
import com.vo.movie.forecast.bot.util.isCommand
import org.telegram.telegrambots.meta.api.objects.Update

abstract class CommandUpdateHandler(private val command: Command) : UpdateHandler() {

    override fun shouldHandle(update: Update): Boolean =
        update.isCommandFromMessage() || update.isCommandFromCallbackQuery()

    private fun Update.isCommandFromMessage(): Boolean =
        hasMessage() && message.isCommand && command.isCommand(message.text)

    private fun Update.isCommandFromCallbackQuery(): Boolean =
        hasCallbackQuery() && command.isCommand(callbackQuery.data)

}