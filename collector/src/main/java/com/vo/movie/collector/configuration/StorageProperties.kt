package com.vo.movie.collector.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "storage")
class StorageProperties {
    var image: File? = null

    class File {
        var path: String? = null
        var extension: String? = null
    }
}