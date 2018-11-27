package com.vo.movie.collector.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import java.net.URI

@ConfigurationProperties(prefix = "parser")
class ParserProperties {
    var kinopoisk: Resource? = null

    class Resource {
        var url: URI? = null
        var premieres: PremieresProperties? = null
        var movie: MovieProperties? = null

        class PremieresProperties {
            var urlTemplate: String? = null
            var premiereLink: PremieresElement? = null

            class PremieresElement : Element() {
                var attribute: String? = null
            }
        }

        class MovieProperties {
            var title: Element? = null
            var property: MovieElement? = null
            var ratingKinopoisk: Element? = null
            var ratingIMDB: Element? = null

            class MovieElement : Element() {
                var delimiter: String? = null
                var unusedLines: List<String> = emptyList()
            }
        }

        open class Element {
            var selector: String? = null
        }
    }
}