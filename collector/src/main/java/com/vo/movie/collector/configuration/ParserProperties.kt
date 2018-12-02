package com.vo.movie.collector.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component
import java.net.URL

@Component
@ConfigurationProperties(prefix = "parser")
class ParserProperties {
    var kinopoisk: Resource? = null

    class Resource {
        var url: URL? = null
        var premieres: PremieresProperties? = null
        var movie: MovieProperties? = null

        class PremieresProperties {
            var urlTemplate: String? = null
            var premiereLink: Element? = null
        }

        class MovieProperties {
            var title: Element? = null
            var poster: Element? = null
            var property: Element? = null
            var ratingKinopoisk: Element? = null
            var ratingIMDB: Element? = null
        }

        open class Element {
            companion object {
                private const val DEFAULT_DELIMITER: String = "_♠_♣_♥_♦_"
            }

            var selector: String? = null
            var attribute: String? = null
            var delimiter: String = DEFAULT_DELIMITER
            var unusedLines: List<String> = emptyList()
        }
    }
}