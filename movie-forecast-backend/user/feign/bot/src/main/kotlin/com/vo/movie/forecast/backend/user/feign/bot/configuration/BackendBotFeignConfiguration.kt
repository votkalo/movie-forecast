package com.vo.movie.forecast.backend.user.feign.bot.configuration

import com.vo.movie.forecast.backend.user.api.bot.MovieApi
import com.vo.movie.forecast.backend.user.api.bot.UserApi
import com.vo.movie.forecast.backend.user.feign.bot.MovieApiFeign
import com.vo.movie.forecast.backend.user.feign.bot.UserApiFeign
import com.vo.movie.forecast.commons.feign.FeignBuilderFactory
import com.vo.movie.forecast.commons.feign.FeignProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class BackendBotFeignConfiguration(private val feignBuilderFactory: FeignBuilderFactory,
                                        private val feignProperties: FeignProperties) {

    @Bean
    open fun backendUserApiForBot(): UserApi = feignBuilderFactory.jsonFeignBuilder()
            .target(UserApiFeign::class.java, feignProperties.movieForecastBackend?.url?.toASCIIString())

    @Bean
    open fun backendMovieApiForBot(): MovieApi = feignBuilderFactory.jsonFeignBuilder()
            .target(MovieApiFeign::class.java, feignProperties.movieForecastBackend?.url?.toASCIIString())
}