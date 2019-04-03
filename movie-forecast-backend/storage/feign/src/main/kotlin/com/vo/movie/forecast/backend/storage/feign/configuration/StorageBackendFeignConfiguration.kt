package com.vo.movie.forecast.backend.storage.feign.configuration

import com.vo.movie.forecast.backend.storage.api.MovieApi
import com.vo.movie.forecast.backend.storage.feign.MovieApiFeign
import com.vo.movie.forecast.commons.feign.FeignBuilderFactory
import com.vo.movie.forecast.commons.feign.FeignProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class StorageBackendFeignConfiguration(private val feignBuilderFactory: FeignBuilderFactory,
                                            private val feignProperties: FeignProperties) {
    @Bean
    open fun botNotificationApiForNotifier(): MovieApi =
        feignBuilderFactory
            .jsonFeignBuilder()
            .target(MovieApiFeign::class.java, feignProperties.movieForecastBackendStorage?.url?.toASCIIString())
}