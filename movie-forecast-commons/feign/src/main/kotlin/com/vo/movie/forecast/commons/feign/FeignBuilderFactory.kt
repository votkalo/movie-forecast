package com.vo.movie.forecast.commons.feign

import feign.Client
import feign.Feign
import feign.Logger
import feign.Retryer
import feign.codec.Decoder
import feign.codec.Encoder
import org.apache.http.HttpHeaders
import org.springframework.http.MediaType

class FeignBuilderFactory(private val feignClient: Client,
                          private val feignDecoder: Decoder,
                          private val feignEncoder: Encoder,
                          private val feignLogger: Logger) {

    fun jsonFeignBuilder(): Feign.Builder =
        Feign.builder()
            .client(feignClient)
            .decoder(feignDecoder)
            .encoder(feignEncoder)
            .logger(feignLogger)
            .logLevel(Logger.Level.FULL)
            .retryer(Retryer.NEVER_RETRY)
            .requestInterceptor { template ->
                template.header(HttpHeaders.ACCEPT_CHARSET, Charsets.UTF_8.name())
                template.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_UTF8_VALUE)
                template.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
            }
}