package com.vo.movie.forecast.backend.user.feign.notifier.configuration

import com.vo.movie.forecast.backend.user.api.notifier.MovieApi
import com.vo.movie.forecast.backend.user.api.notifier.UserApi
import com.vo.movie.forecast.backend.user.feign.notifier.MovieApiFeign
import com.vo.movie.forecast.backend.user.feign.notifier.UserApiFeign
import com.vo.movie.forecast.commons.feign.FeignBuilderFactory
import com.vo.movie.forecast.commons.feign.FeignProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class UserBackendNotifierFeignConfiguration(private val feignBuilderFactory: FeignBuilderFactory,
                                                 private val feignProperties: FeignProperties) {

    @Bean
    open fun backendUserApiForNotifier(): UserApi =
        feignBuilderFactory
            .jsonFeignBuilder()
            .target(UserApiFeign::class.java, feignProperties.movieForecastBackendUser?.url?.toASCIIString())

    @Bean
    open fun backendMovieApiForNotifier(): MovieApi =
        feignBuilderFactory
            .jsonFeignBuilder()
            .target(MovieApiFeign::class.java, feignProperties.movieForecastBackendUser?.url?.toASCIIString())
}