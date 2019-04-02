package com.vo.movie.forecast.parser.feign.locality.configuration

import com.vo.movie.forecast.commons.feign.FeignBuilderFactory
import com.vo.movie.forecast.commons.feign.FeignProperties
import com.vo.movie.forecast.parser.api.locality.LocalityApi
import com.vo.movie.forecast.parser.feign.locality.LocalityApiFeign
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class LocalityFeignConfiguration(private val feignBuilderFactory: FeignBuilderFactory,
                                      private val feignProperties: FeignProperties) {
    @Bean
    open fun parserLocalityApi(): LocalityApi = feignBuilderFactory.jsonFeignBuilder()
            .target(LocalityApiFeign::class.java, feignProperties.movieForecastParser?.url?.toASCIIString())
}