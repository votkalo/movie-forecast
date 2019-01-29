package com.vo.movie.forecast.backend.feign.configuration

import com.vo.movie.forecast.backend.api.api.FollowApi
import com.vo.movie.forecast.backend.feign.FollowApiFeign
import com.vo.movie.forecast.commons.feign.FeignBuilderFactory
import com.vo.movie.forecast.commons.feign.FeignProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class MovieForecastBackendFeignConfiguration(private val feignBuilderFactory: FeignBuilderFactory,
                                                  private val feignProperties: FeignProperties) {

    @Bean
    open fun userApi(): FollowApi = feignBuilderFactory.jsonFeignBuilder()
            .target(FollowApiFeign::class.java, feignProperties.movieForecastBackend?.url?.toASCIIString())
}