package com.vo.movie.page.reader.feign

import com.vo.movie.commons.feign.FeignBuilderFactory
import com.vo.movie.commons.feign.FeignProperties
import com.vo.movie.page.reader.api.PageReaderApi
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class PageReaderFeignConfiguration(private val feignBuilderFactory: FeignBuilderFactory,
                                        private val feignProperties: FeignProperties) {

    @Bean
    open fun pageReaderApi(): PageReaderApi = feignBuilderFactory.jsonFeignBuilder()
        .target(PageReaderApiFeign::class.java, feignProperties.pageReader?.url?.toASCIIString())
}