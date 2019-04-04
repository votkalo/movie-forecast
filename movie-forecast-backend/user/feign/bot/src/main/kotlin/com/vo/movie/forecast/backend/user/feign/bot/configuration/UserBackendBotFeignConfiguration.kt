package com.vo.movie.forecast.backend.user.feign.bot.configuration

import com.vo.movie.forecast.backend.user.api.bot.UserApi
import com.vo.movie.forecast.backend.user.api.bot.UserMovieApi
import com.vo.movie.forecast.backend.user.feign.bot.MovieApiFeign
import com.vo.movie.forecast.backend.user.feign.bot.UserApiFeign
import com.vo.movie.forecast.commons.feign.FeignBuilderFactory
import com.vo.movie.forecast.commons.feign.FeignProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class UserBackendBotFeignConfiguration(private val feignBuilderFactory: FeignBuilderFactory,
                                            private val feignProperties: FeignProperties) {

    @Bean
    open fun backendUserApiForBot(): UserApi =
        feignBuilderFactory
            .jsonFeignBuilder()
            .target(UserApiFeign::class.java, feignProperties.movieForecastBackendUser?.url?.toASCIIString())

    @Bean
    open fun backendMovieApiForBot(): UserMovieApi =
        feignBuilderFactory
            .jsonFeignBuilder()
            .target(MovieApiFeign::class.java, feignProperties.movieForecastBackendUser?.url?.toASCIIString())
}