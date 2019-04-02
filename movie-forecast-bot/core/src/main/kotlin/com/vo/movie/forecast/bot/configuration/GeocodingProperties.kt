package com.vo.movie.forecast.bot.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "geocoding")
class GeocodingProperties {
    var apiKey: String? = null
}