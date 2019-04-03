package com.vo.movie.forecast.commons.feign

import com.fasterxml.jackson.databind.ObjectMapper
import feign.Client
import feign.Logger
import feign.codec.Decoder
import feign.codec.Encoder
import feign.httpclient.ApacheHttpClient
import feign.jackson.JacksonDecoder
import feign.jackson.JacksonEncoder
import feign.slf4j.Slf4jLogger
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(FeignProperties::class)
open class FeignConfiguration(private val objectMapper: ObjectMapper) {

    @Bean
    open fun feignClient(): Client = ApacheHttpClient()

    @Bean
    open fun feignDecoder(): Decoder = JacksonDecoder(objectMapper)

    @Bean
    open fun feignEncoder(): Encoder = JacksonEncoder(objectMapper)

    @Bean
    open fun feignLogger(): Logger = Slf4jLogger()

    @Bean
    open fun feignBuilderFactory(feignClient: Client,
                                 feignDecoder: Decoder,
                                 feignEncoder: Encoder,
                                 feignLogger: Logger): FeignBuilderFactory =
        FeignBuilderFactory(feignClient, feignDecoder, feignEncoder, feignLogger)
}