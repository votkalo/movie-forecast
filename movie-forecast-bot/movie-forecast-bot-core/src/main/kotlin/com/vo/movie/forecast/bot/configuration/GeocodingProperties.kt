package com.vo.movie.forecast.bot.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.PropertySource
import org.springframework.stereotype.Component

@Component
@PropertySource("classpath:geocoding.properties")
@ConfigurationProperties(prefix = "geocoding")
class GeocodingProperties {
    var apiKey: String? = null
}