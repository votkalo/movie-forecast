package com.vo.movie.forecast.bot.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource

@Configuration
@PropertySource("classpath:bot.properties")
@ConfigurationProperties(prefix = "bot")
open class BotProperties {
    var username: String? = null
    var token: String? = null
}