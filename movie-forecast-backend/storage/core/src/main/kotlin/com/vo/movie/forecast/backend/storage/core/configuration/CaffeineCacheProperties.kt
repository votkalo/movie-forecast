package com.vo.movie.forecast.backend.storage.core.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "caffeine")
class CaffeineCacheProperties {
    var movie: CacheProperties =
        CacheProperties()

    class CacheProperties {
        var maxSize: Long = 100
        var days: Long = 1
    }
}