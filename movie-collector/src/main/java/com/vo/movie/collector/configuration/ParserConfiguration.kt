package com.vo.movie.collector.configuration

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(ParserProperties::class)
open class ParserConfiguration