package com.vo.movie.forecast.parser.feign.movie.configuration

import com.vo.movie.forecast.commons.feign.FeignBuilderFactory
import com.vo.movie.forecast.commons.feign.FeignProperties
import com.vo.movie.forecast.parser.api.movie.MovieApi
import com.vo.movie.forecast.parser.feign.movie.MovieApiFeign
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class MovieFeignConfiguration(private val feignBuilderFactory: FeignBuilderFactory,
                                   private val feignProperties: FeignProperties) {

    @Bean
    open fun movieApi(): MovieApi = feignBuilderFactory.jsonFeignBuilder()
            .target(MovieApiFeign::class.java, feignProperties.movieForecastParser?.url?.toASCIIString())

}