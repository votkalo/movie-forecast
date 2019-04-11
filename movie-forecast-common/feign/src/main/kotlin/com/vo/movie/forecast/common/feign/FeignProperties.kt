package com.vo.movie.forecast.common.feign

import org.springframework.boot.context.properties.ConfigurationProperties
import java.net.URI

@ConfigurationProperties(prefix = "feign")
open class FeignProperties {
    var movieForecastParser: FeignURL? = null
    var movieForecastBackendUser: FeignURL? = null
    var movieForecastBackendStorage: FeignURL? = null
    var movieForecastBot: FeignURL? = null

    class FeignURL {
        var url: URI? = null
    }
}
