package com.vo.movie.forecast.common.cache

import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableCaching
open class ExtendableCacheConfiguration {

    @Bean
    open fun cacheManager(): ExtendableSimpleCacheManager =
        ExtendableSimpleCacheManager()
}