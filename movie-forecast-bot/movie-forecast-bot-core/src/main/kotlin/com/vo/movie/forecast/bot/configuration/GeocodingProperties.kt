package com.vo.movie.forecast.bot.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.stereotype.Component

@Component
@Configuration
@PropertySource("classpath:geocoding.properties")
@ConfigurationProperties(prefix = "geocoding")
open class GeocodingProperties {
    var apiKey: String? = null
}