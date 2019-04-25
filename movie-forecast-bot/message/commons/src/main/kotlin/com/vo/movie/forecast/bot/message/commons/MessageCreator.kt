package com.vo.movie.forecast.bot.message.commons

abstract class MessageCreator {

    protected fun valueOrEmpty(obj: Any?, value: String) = if (obj != null) value else ""

    protected fun valueOrEmpty(obj: String?, value: String) = if (!obj.isNullOrBlank()) value else ""

    protected fun valueOrEmpty(obj: Boolean?, value: String) = if (obj != null && obj) value else ""

    protected fun valueOrEmpty(obj: Any?) = obj?.toString() ?: ""
}