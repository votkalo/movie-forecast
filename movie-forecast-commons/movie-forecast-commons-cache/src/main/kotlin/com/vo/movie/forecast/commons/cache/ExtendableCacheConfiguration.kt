package com.vo.movie.forecast.commons.cache

import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableCaching
open class ExtendableCacheConfiguration {

    @Bean
    open fun extendableSimpleCacheManager(): CacheManager = ExtendableSimpleCacheManager()
}