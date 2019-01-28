package com.vo.movie.forecast.bot.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.PropertySource
import org.springframework.stereotype.Component

@Component
@PropertySource("classpath:bot.properties")
@ConfigurationProperties(prefix = "bot")
class BotProperties {
    var username: String? = null
    var token: String? = null
}