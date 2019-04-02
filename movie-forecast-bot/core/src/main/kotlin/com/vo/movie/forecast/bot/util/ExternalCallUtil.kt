package com.vo.movie.forecast.bot.util

import com.vo.movie.forecast.bot.handler.UpdateHandler
import feign.FeignException

fun <T> UpdateHandler.call(func: () -> T, chatId: Long): T {
    try {
        return func.invoke()
    } catch (e: FeignException) {
        getBot().execute(createMessage(chatId, "К сожалению данная функция сейчас не доступна"))
        throw e
    }
}
