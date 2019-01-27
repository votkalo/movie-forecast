package com.vo.movie.forecast.parser.provider

import com.vo.movie.forecast.parser.api.LocalityApi
import com.vo.movie.forecast.parser.dto.Locality
import com.vo.movie.forecast.parser.provider.configuration.MovieForecastParserCacheConfiguration.Companion.LOCALITIES_BY_LETTER_CACHE_NAME
import com.vo.movie.forecast.parser.provider.configuration.MovieForecastParserCacheConfiguration.Companion.LOCALITIES_CACHE_NAME
import com.vo.movie.forecast.parser.provider.configuration.MovieForecastParserCacheConfiguration.Companion.LOCALITY_LETTERS_CACHE_NAME
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Component

@Component
open class LocalityCacheableProvider(private val localityApi: LocalityApi) : LocalityProvider {

    @Cacheable(cacheNames = [LOCALITIES_CACHE_NAME])
    override fun getLocalities(): List<Locality> = localityApi.getLocalities()

    @Cacheable(cacheNames = [LOCALITY_LETTERS_CACHE_NAME])
    override fun getLocalitiesLetters(): List<String> = getLocalities()
            .map { it.name.first().toString() }
            .distinct()
            .sorted()

    @Cacheable(cacheNames = [LOCALITIES_BY_LETTER_CACHE_NAME])
    override fun getLocalitiesByLetter(letter: Char): List<Locality> = getLocalities()
            .filter { it.name.first() == letter }

}