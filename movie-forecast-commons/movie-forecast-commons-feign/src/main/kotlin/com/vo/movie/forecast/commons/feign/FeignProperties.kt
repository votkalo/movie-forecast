package com.vo.movie.forecast.commons.feign

import org.springframework.boot.context.properties.ConfigurationProperties
import java.net.URI

@ConfigurationProperties(prefix = "feign")
open class FeignProperties {
    var movieForecastParser: FeignURL? = null
    var movieForecastBackend: FeignURL? = null

    class FeignURL {
        var url: URI? = null
    }
}
