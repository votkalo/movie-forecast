package com.vo.movie.forecast.parser.provider.movie.configuration

import com.github.benmanes.caffeine.cache.Cache
import com.github.benmanes.caffeine.cache.Caffeine
import com.vo.movie.forecast.backend.storage.data.MovieDTO
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.Duration


@Configuration
@EnableCaching
open class MovieCacheConfiguration(private val caffeineCacheProperties: CaffeineCacheProperties) {

    @Bean
    open fun movieCaffeineCache(): Cache<Long, MovieDTO> {
        val movieCacheProperties = caffeineCacheProperties.movie
        return Caffeine.newBuilder()
                .expireAfterAccess(Duration.ofDays(movieCacheProperties.days))
                .maximumSize(movieCacheProperties.maxSize)
            .build<Long, MovieDTO>()
    }
}