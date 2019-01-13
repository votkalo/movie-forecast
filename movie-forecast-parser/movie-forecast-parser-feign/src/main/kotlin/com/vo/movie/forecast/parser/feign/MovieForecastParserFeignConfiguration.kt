package com.vo.movie.forecast.parser.feign

import com.vo.movie.forecast.commons.feign.FeignBuilderFactory
import com.vo.movie.forecast.commons.feign.FeignProperties
import com.vo.movie.forecast.parser.api.MovieForecastParserApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan("com.vo.movie")
open class MovieForecastParserFeignConfiguration(private val feignBuilderFactory: FeignBuilderFactory,
                                                 private val feignProperties: FeignProperties) {

    @Bean
    open fun movieForecastParserApi(): MovieForecastParserApi = feignBuilderFactory.jsonFeignBuilder()
            .target(MovieForecastParserApiFeign::class.java, feignProperties.movieForecastParser?.url?.toASCIIString())
}