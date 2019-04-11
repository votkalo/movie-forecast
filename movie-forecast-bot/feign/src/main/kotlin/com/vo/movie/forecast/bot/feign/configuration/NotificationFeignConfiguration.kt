package com.vo.movie.forecast.bot.feign.configuration

import com.vo.movie.forecast.bot.api.NotificationApi
import com.vo.movie.forecast.bot.feign.NotificationApiFeign
import com.vo.movie.forecast.common.feign.FeignBuilderFactory
import com.vo.movie.forecast.common.feign.FeignProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class NotificationFeignConfiguration(private val feignBuilderFactory: FeignBuilderFactory,
                                          private val feignProperties: FeignProperties) {
    @Bean
    open fun botNotificationApiForNotifier(): NotificationApi =
        feignBuilderFactory
            .jsonFeignBuilder()
            .target(NotificationApiFeign::class.java, feignProperties.movieForecastBot?.url?.toASCIIString())
}