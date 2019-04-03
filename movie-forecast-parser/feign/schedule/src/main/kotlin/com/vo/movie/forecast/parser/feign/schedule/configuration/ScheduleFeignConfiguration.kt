package com.vo.movie.forecast.parser.feign.schedule.configuration

import com.vo.movie.forecast.commons.feign.FeignBuilderFactory
import com.vo.movie.forecast.commons.feign.FeignProperties
import com.vo.movie.forecast.parser.api.schedule.ScheduleApi
import com.vo.movie.forecast.parser.feign.schedule.ScheduleApiFeign
import feign.Retryer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class ScheduleFeignConfiguration(private val feignBuilderFactory: FeignBuilderFactory,
                                      private val feignProperties: FeignProperties) {

    @Bean
    open fun parserScheduleApi(): ScheduleApi =
        feignBuilderFactory
            .jsonFeignBuilder()
            .retryer(Retryer.Default())
            .target(ScheduleApiFeign::class.java, feignProperties.movieForecastParser?.url?.toASCIIString())
}