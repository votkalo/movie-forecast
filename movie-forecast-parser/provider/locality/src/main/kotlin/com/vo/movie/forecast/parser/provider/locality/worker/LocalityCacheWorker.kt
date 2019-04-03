package com.vo.movie.forecast.parser.provider.locality.worker

import com.vo.movie.forecast.backend.storage.data.LocalityDTO
import com.vo.movie.forecast.commons.cache.ExtendableSimpleCacheManager
import com.vo.movie.forecast.parser.provider.locality.LocalityProvider
import com.vo.movie.forecast.parser.provider.locality.configuration.LocalityCacheConfiguration.Companion.LOCALITIES_CACHE_NAME
import com.vo.movie.forecast.parser.provider.locality.configuration.LocalityCacheConfiguration.Companion.LOCALITIES_LETTERS_CACHE_NAME
import com.vo.movie.forecast.parser.provider.locality.configuration.LocalityCacheConfiguration.Companion.LOCALITIES_NAMES_BY_LETTER_CACHE_NAME
import com.vo.movie.forecast.parser.provider.locality.configuration.LocalityCacheConfiguration.Companion.LOCALITY_BY_NAME_CACHE_NAME
import com.vo.movie.forecast.parser.provider.locality.util.getLocalitiesLetters
import com.vo.movie.forecast.parser.provider.locality.util.getLocalitiesNamesByLetter
import com.vo.movie.forecast.parser.provider.locality.util.getLocalityByName
import org.springframework.cache.interceptor.SimpleKey
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct


@Component
class LocalityCacheWorker(private val extendableSimpleCacheManager: ExtendableSimpleCacheManager,
                          private val localityProvider: LocalityProvider) {

    @Scheduled(cron = "0 0 0 1 * ?", zone = "Europe/Minsk")
    fun cleanUpdateCaches() {
        extendableSimpleCacheManager.clearCaches(
                LOCALITIES_CACHE_NAME,
                LOCALITIES_LETTERS_CACHE_NAME,
                LOCALITIES_NAMES_BY_LETTER_CACHE_NAME,
                LOCALITY_BY_NAME_CACHE_NAME
        )
        updateCaches()
    }

    @PostConstruct
    private fun updateCaches() {
        val localities = updateLocalitiesCache()
        val localitiesLetters = updateLocalitiesLettersCache(localities)
        updateLocalitiesNamesByLetterCache(localities, localitiesLetters)
        updateLocalityByNameCache(localities)
    }

    private fun updateLocalitiesCache(): List<LocalityDTO> {
        val localities = localityProvider.getLocalities()
        extendableSimpleCacheManager.getCache(LOCALITIES_CACHE_NAME)?.putIfAbsent(SimpleKey.EMPTY, localities)
        return localities
    }

    private fun updateLocalitiesLettersCache(localities: List<LocalityDTO>): List<String> {
        val localitiesLetters = localities.getLocalitiesLetters()
        extendableSimpleCacheManager.getCache(LOCALITIES_LETTERS_CACHE_NAME)?.putIfAbsent(SimpleKey.EMPTY, localitiesLetters)
        return localitiesLetters
    }

    private fun updateLocalitiesNamesByLetterCache(localities: List<LocalityDTO>, localitiesLetters: List<String>) {
        val localitiesNamesByLetterCache = extendableSimpleCacheManager.getCache(LOCALITIES_NAMES_BY_LETTER_CACHE_NAME)
        localitiesLetters.forEach {
            val localityLetter = it.first()
            val localitiesNamesByLetter = localities.getLocalitiesNamesByLetter(localityLetter)
            localitiesNamesByLetterCache?.put(localityLetter, localitiesNamesByLetter)
        }
    }

    private fun updateLocalityByNameCache(localities: List<LocalityDTO>) {
        val localityByNameCache = extendableSimpleCacheManager.getCache(LOCALITY_BY_NAME_CACHE_NAME)
        localities.forEach {
            val localityName = it.name
            val localityByName = localities.getLocalityByName(localityName)
            localityByNameCache?.put(localityName, localityByName)
        }
    }
}