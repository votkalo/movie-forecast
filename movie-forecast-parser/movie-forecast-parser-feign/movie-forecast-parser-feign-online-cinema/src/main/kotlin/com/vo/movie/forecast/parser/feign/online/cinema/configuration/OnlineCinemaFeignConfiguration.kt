package com.vo.movie.forecast.parser.feign.online.cinema.configuration

import com.vo.movie.forecast.commons.feign.FeignBuilderFactory
import com.vo.movie.forecast.commons.feign.FeignProperties
import com.vo.movie.forecast.parser.api.online.cinema.OnlineCinemaApi
import com.vo.movie.forecast.parser.feign.online.cinema.OnlineCinemaApiFeign
import feign.Retryer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class OnlineCinemaFeignConfiguration(private val feignBuilderFactory: FeignBuilderFactory,
                                          private val feignProperties: FeignProperties) {

    @Bean
    open fun onlineCinemaApi(): OnlineCinemaApi = feignBuilderFactory.jsonFeignBuilder()
            .retryer(Retryer.Default())
            .target(OnlineCinemaApiFeign::class.java, feignProperties.movieForecastParser?.url?.toASCIIString())
}