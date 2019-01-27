package com.vo.movie.forecast.bot.handler.callback

import com.vo.movie.forecast.bot.handler.UpdateHandler
import com.vo.movie.forecast.bot.util.isCallbackData
import com.vo.movie.forecast.bot.util.removeCallbackPrefix
import org.telegram.telegrambots.meta.api.objects.Update

abstract class CallbackUpdateHandler(private val callback: Callback) : UpdateHandler() {

    override fun shouldHandle(update: Update): Boolean =
            update.hasCallbackQuery() && callback.isCallbackData(update.callbackQuery.data)

    protected fun Update.getCallbackData(): String = callback.removeCallbackPrefix(callbackQuery.data)
}