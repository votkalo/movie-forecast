package com.vo.movie.forecast.common.feign

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class ObjectMapperConfiguration {

    companion object {
        const val EXTENDED_OBJECT_MAPPER_QUALIFIER = "extendedObjectMapper"
    }

    @Bean(EXTENDED_OBJECT_MAPPER_QUALIFIER)
    open fun objectMapper(): ObjectMapper =
        ObjectMapper()
            .registerModule(JavaTimeModule())
            .registerModule(Jdk8Module())
            .registerModule(KotlinModule())

}