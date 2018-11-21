package com.vo.movie.collector.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import java.net.URI

@ConfigurationProperties(prefix = "parser")
class ParserProperties {
    var kinopoisk: Resource? = null

    class Resource {
        var url: URI? = null
        var premieres: Properties? = null

        class Properties {
            var urlTemplate: String? = null
            var selector: String? = null
            var attribute: String? = null
        }
    }
}