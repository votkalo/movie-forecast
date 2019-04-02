package com.vo.movie.forecast.bot.util

import com.vo.movie.forecast.bot.handler.callback.Callback

fun Callback.isCallbackData(data: String) = data.startsWith(value)

fun Callback.removeCallbackPrefix(data: String) = data.removePrefix(value)

fun Callback.addCallbackPrefix(data: String): String = value + data