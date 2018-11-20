package com.vo.movie.page.reader.configuration

import com.vo.movie.commons.feign.FeignBuilderFactory
import com.vo.movie.commons.feign.FeignProperties
import com.vo.movie.page.reader.api.PageReaderApiExternal
import com.vo.movie.page.reader.api.PageReaderApiExternalFeign
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class PageReaderExternalFeignConfiguration(private val feignBuilderFactory: FeignBuilderFactory,
                                                private val feignProperties: FeignProperties) {

    @Bean
    open fun pageReaderApiExternal(): PageReaderApiExternal = feignBuilderFactory.jsonFeignBuilder()
        .target(PageReaderApiExternalFeign::class.java, feignProperties.pageReaderExternal?.url?.toASCIIString())
}