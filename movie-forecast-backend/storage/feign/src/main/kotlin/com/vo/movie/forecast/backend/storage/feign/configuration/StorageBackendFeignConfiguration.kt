package com.vo.movie.forecast.backend.storage.feign.configuration

import com.vo.movie.forecast.backend.storage.api.LocalityApi
import com.vo.movie.forecast.backend.storage.api.MovieApi
import com.vo.movie.forecast.backend.storage.api.OnlineCinemaApi
import com.vo.movie.forecast.backend.storage.api.ScheduleApi
import com.vo.movie.forecast.backend.storage.feign.LocalityApiFeign
import com.vo.movie.forecast.backend.storage.feign.MovieApiFeign
import com.vo.movie.forecast.backend.storage.feign.OnlineCinemaFeign
import com.vo.movie.forecast.backend.storage.feign.ScheduleApiFeign
import com.vo.movie.forecast.common.feign.FeignBuilderFactory
import com.vo.movie.forecast.common.feign.FeignProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class StorageBackendFeignConfiguration(private val feignBuilderFactory: FeignBuilderFactory,
                                            private val feignProperties: FeignProperties) {
    @Bean
    open fun storageBackendMovieApi(): MovieApi =
        feignBuilderFactory
            .jsonFeignBuilder()
            .target(MovieApiFeign::class.java, feignProperties.movieForecastBackendStorage?.url?.toASCIIString())

    @Bean
    open fun storageBackendLocalityApi(): LocalityApi =
        feignBuilderFactory
            .jsonFeignBuilder()
            .target(LocalityApiFeign::class.java, feignProperties.movieForecastBackendStorage?.url?.toASCIIString())

    @Bean
    open fun storageBackendScheduleApi(): ScheduleApi =
        feignBuilderFactory
            .jsonFeignBuilder()
            .target(ScheduleApiFeign::class.java, feignProperties.movieForecastBackendStorage?.url?.toASCIIString())

    @Bean
    open fun storageBackendOnlineCinemaApi(): OnlineCinemaApi =
        feignBuilderFactory
            .jsonFeignBuilder()
            .target(OnlineCinemaFeign::class.java, feignProperties.movieForecastBackendStorage?.url?.toASCIIString())
}