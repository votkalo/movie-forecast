package com.vo.movie.forecast.bot.util

import com.vo.movie.forecast.bot.handler.command.Command

fun Command.isCommand(data: String) = data == value
