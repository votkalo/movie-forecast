package com.vo.movie.forecast.parser.provider.locality

import com.vo.movie.forecast.commons.data.Locality
import com.vo.movie.forecast.parser.api.locality.LocalityApi
import com.vo.movie.forecast.parser.provider.locality.configuration.LocalityCacheConfiguration.Companion.LOCALITIES_CACHE_NAME
import com.vo.movie.forecast.parser.provider.locality.configuration.LocalityCacheConfiguration.Companion.LOCALITIES_LETTERS_CACHE_NAME
import com.vo.movie.forecast.parser.provider.locality.configuration.LocalityCacheConfiguration.Companion.LOCALITIES_NAMES_BY_LETTER_CACHE_NAME
import com.vo.movie.forecast.parser.provider.locality.configuration.LocalityCacheConfiguration.Companion.LOCALITY_BY_NAME_CACHE_NAME
import com.vo.movie.forecast.parser.provider.locality.configuration.LocalityCacheConfiguration.Companion.LOCALITY_CACHE_MANAGER
import com.vo.movie.forecast.parser.provider.locality.util.getLocalitiesLetters
import com.vo.movie.forecast.parser.provider.locality.util.getLocalitiesNamesByLetter
import com.vo.movie.forecast.parser.provider.locality.util.getLocalityByName
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Component

@Component
open class LocalityCacheableProvider(private val localityApi: LocalityApi) : LocalityProvider {

    @Cacheable(cacheNames = [LOCALITIES_CACHE_NAME], cacheManager = LOCALITY_CACHE_MANAGER)
    override fun getLocalities(): List<Locality> = localityApi.getLocalities()

    @Cacheable(cacheNames = [LOCALITIES_LETTERS_CACHE_NAME], cacheManager = LOCALITY_CACHE_MANAGER)
    override fun getLocalitiesLetters(): List<String> = getLocalities().getLocalitiesLetters()

    @Cacheable(cacheNames = [LOCALITIES_NAMES_BY_LETTER_CACHE_NAME], cacheManager = LOCALITY_CACHE_MANAGER)
    override fun getLocalitiesNamesByLetter(letter: Char): List<String> = getLocalities().getLocalitiesNamesByLetter(letter)

    @Cacheable(cacheNames = [LOCALITY_BY_NAME_CACHE_NAME], cacheManager = LOCALITY_CACHE_MANAGER)
    override fun getLocalityByName(name: String): Locality = getLocalities().getLocalityByName(name)
}