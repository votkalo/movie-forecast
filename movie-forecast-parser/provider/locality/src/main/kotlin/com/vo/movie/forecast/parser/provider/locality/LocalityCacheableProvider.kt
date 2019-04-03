package com.vo.movie.forecast.parser.provider.locality

import com.vo.movie.forecast.backend.storage.data.LocalityDTO
import com.vo.movie.forecast.parser.api.locality.LocalityApi
import com.vo.movie.forecast.parser.provider.locality.configuration.LocalityCacheConfiguration.Companion.LOCALITIES_CACHE_NAME
import com.vo.movie.forecast.parser.provider.locality.configuration.LocalityCacheConfiguration.Companion.LOCALITIES_LETTERS_CACHE_NAME
import com.vo.movie.forecast.parser.provider.locality.configuration.LocalityCacheConfiguration.Companion.LOCALITIES_NAMES_BY_LETTER_CACHE_NAME
import com.vo.movie.forecast.parser.provider.locality.configuration.LocalityCacheConfiguration.Companion.LOCALITY_BY_NAME_CACHE_NAME
import com.vo.movie.forecast.parser.provider.locality.util.getLocalitiesLetters
import com.vo.movie.forecast.parser.provider.locality.util.getLocalitiesNamesByLetter
import com.vo.movie.forecast.parser.provider.locality.util.getLocalityByName
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Component

@Component
open class LocalityCacheableProvider(private val localityApi: LocalityApi) : LocalityProvider {

    @Cacheable(cacheNames = [LOCALITIES_CACHE_NAME])
    override fun getLocalities(): List<LocalityDTO> = localityApi.getLocalities()

    @Cacheable(cacheNames = [LOCALITIES_LETTERS_CACHE_NAME])
    override fun getLocalitiesLetters(): List<String> = getLocalities().getLocalitiesLetters()

    @Cacheable(cacheNames = [LOCALITIES_NAMES_BY_LETTER_CACHE_NAME])
    override fun getLocalitiesNamesByLetter(letter: Char): List<String> = getLocalities().getLocalitiesNamesByLetter(letter)

    @Cacheable(cacheNames = [LOCALITY_BY_NAME_CACHE_NAME])
    override fun getLocalityByName(name: String): LocalityDTO = getLocalities().getLocalityByName(name)
}