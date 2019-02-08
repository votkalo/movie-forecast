package com.vo.movie.forecast.parser.provider.impl

import com.vo.movie.forecast.commons.data.Locality
import com.vo.movie.forecast.parser.api.LocalityApi
import com.vo.movie.forecast.parser.provider.LocalityProvider
import com.vo.movie.forecast.parser.provider.configuration.MovieForecastParserCacheConfiguration.Companion.LOCALITIES_CACHE_NAME
import com.vo.movie.forecast.parser.provider.configuration.MovieForecastParserCacheConfiguration.Companion.LOCALITIES_LETTERS_CACHE_NAME
import com.vo.movie.forecast.parser.provider.configuration.MovieForecastParserCacheConfiguration.Companion.LOCALITIES_NAMES_BY_LETTER_CACHE_NAME
import com.vo.movie.forecast.parser.provider.configuration.MovieForecastParserCacheConfiguration.Companion.LOCALITY_BY_NAME_CACHE_NAME
import com.vo.movie.forecast.parser.provider.util.getLocalitiesLetters
import com.vo.movie.forecast.parser.provider.util.getLocalitiesNamesByLetter
import com.vo.movie.forecast.parser.provider.util.getLocalityByName
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Component

@Component
open class LocalityCacheableProvider(private val localityApi: LocalityApi) : LocalityProvider {

    @Cacheable(cacheNames = [LOCALITIES_CACHE_NAME])
    override fun getLocalities(): List<Locality> = localityApi.getLocalities()

    @Cacheable(cacheNames = [LOCALITIES_LETTERS_CACHE_NAME])
    override fun getLocalitiesLetters(): List<String> = getLocalities().getLocalitiesLetters()

    @Cacheable(cacheNames = [LOCALITIES_NAMES_BY_LETTER_CACHE_NAME])
    override fun getLocalitiesNamesByLetter(letter: Char): List<String> = getLocalities().getLocalitiesNamesByLetter(letter)

    @Cacheable(cacheNames = [LOCALITY_BY_NAME_CACHE_NAME])
    override fun getLocalityByName(name: String): Locality = getLocalities().getLocalityByName(name)
}