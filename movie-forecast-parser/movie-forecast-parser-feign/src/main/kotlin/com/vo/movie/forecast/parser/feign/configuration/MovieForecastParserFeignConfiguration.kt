package com.vo.movie.forecast.parser.feign.configuration

import com.vo.movie.forecast.commons.feign.FeignBuilderFactory
import com.vo.movie.forecast.commons.feign.FeignProperties
import com.vo.movie.forecast.parser.api.LocalityApi
import com.vo.movie.forecast.parser.api.MovieApi
import com.vo.movie.forecast.parser.api.ScheduleApi
import com.vo.movie.forecast.parser.feign.LocalityApiFeign
import com.vo.movie.forecast.parser.feign.MovieApiFeign
import com.vo.movie.forecast.parser.feign.ScheduleApiFeign
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class MovieForecastParserFeignConfiguration(private val feignBuilderFactory: FeignBuilderFactory,
                                                 private val feignProperties: FeignProperties) {

    @Bean
    open fun movieApi(): MovieApi = feignBuilderFactory.jsonFeignBuilder()
            .target(MovieApiFeign::class.java, feignProperties.movieForecastParser?.url?.toASCIIString())

    @Bean
    open fun localityApi(): LocalityApi = feignBuilderFactory.jsonFeignBuilder()
            .target(LocalityApiFeign::class.java, feignProperties.movieForecastParser?.url?.toASCIIString())

    @Bean
    open fun scheduleApi(): ScheduleApi = feignBuilderFactory.jsonFeignBuilder()
            .target(ScheduleApiFeign::class.java, feignProperties.movieForecastParser?.url?.toASCIIString())
}