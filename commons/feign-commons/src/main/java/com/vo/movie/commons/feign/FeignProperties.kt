package com.vo.movie.commons.feign

import org.springframework.boot.context.properties.ConfigurationProperties
import java.net.URI

@ConfigurationProperties(prefix = "feign")
open class FeignProperties {
    var pageReader: FeignURL? = null
    var pageReaderExternal: FeignURL? = null

    class FeignURL {
        var url: URI? = null
    }
}
