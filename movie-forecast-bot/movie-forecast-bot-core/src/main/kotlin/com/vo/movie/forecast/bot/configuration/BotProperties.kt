package com.vo.movie.forecast.bot.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "bot")
class BotProperties {
    var username: String? = null
    var token: String? = null
}