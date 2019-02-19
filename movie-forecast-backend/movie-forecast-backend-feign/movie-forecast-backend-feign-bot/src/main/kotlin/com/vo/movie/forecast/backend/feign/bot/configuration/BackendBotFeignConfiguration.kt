package com.vo.movie.forecast.backend.feign.bot.configuration

import com.vo.movie.forecast.backend.api.bot.UserApi
import com.vo.movie.forecast.backend.feign.bot.UserApiFeign
import com.vo.movie.forecast.commons.feign.FeignBuilderFactory
import com.vo.movie.forecast.commons.feign.FeignProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class BackendBotFeignConfiguration(private val feignBuilderFactory: FeignBuilderFactory,
                                        private val feignProperties: FeignProperties) {

    @Bean
    open fun userApi(): UserApi = feignBuilderFactory.jsonFeignBuilder()
            .target(UserApiFeign::class.java, feignProperties.movieForecastBackend?.url?.toASCIIString())
}