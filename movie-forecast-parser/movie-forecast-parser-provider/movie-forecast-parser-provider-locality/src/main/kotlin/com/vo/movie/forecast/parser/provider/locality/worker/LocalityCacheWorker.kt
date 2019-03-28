package com.vo.movie.forecast.parser.provider.locality.worker

import com.vo.movie.forecast.commons.data.Locality
import com.vo.movie.forecast.parser.provider.locality.LocalityProvider
import com.vo.movie.forecast.parser.provider.locality.configuration.LocalityCacheConfiguration.Companion.LOCALITIES_CACHE_NAME
import com.vo.movie.forecast.parser.provider.locality.configuration.LocalityCacheConfiguration.Companion.LOCALITIES_LETTERS_CACHE_NAME
import com.vo.movie.forecast.parser.provider.locality.configuration.LocalityCacheConfiguration.Companion.LOCALITIES_NAMES_BY_LETTER_CACHE_NAME
import com.vo.movie.forecast.parser.provider.locality.configuration.LocalityCacheConfiguration.Companion.LOCALITY_BY_NAME_CACHE_NAME
import com.vo.movie.forecast.parser.provider.locality.util.getLocalitiesLetters
import com.vo.movie.forecast.parser.provider.locality.util.getLocalitiesNamesByLetter
import com.vo.movie.forecast.parser.provider.locality.util.getLocalityByName
import org.springframework.cache.CacheManager
import org.springframework.cache.interceptor.SimpleKey
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct


@Component
class LocalityCacheWorker(private val extendableSimpleCacheManager: CacheManager,
                          private val localityProvider: LocalityProvider) {

    @Scheduled(cron = "0 0 0 1 * ?", zone = "Europe/Minsk")
    fun cleanUpdateCaches() {
        extendableSimpleCacheManager.cacheNames.forEach { extendableSimpleCacheManager.getCache(it)?.clear() }
        updateCaches()
    }

    @PostConstruct
    private fun updateCaches() {
        val localities = updateLocalitiesCache()
        val localitiesLetters = updateLocalitiesLettersCache(localities)
        updateLocalitiesNamesByLetterCache(localities, localitiesLetters)
        updateLocalityByNameCache(localities)
    }

    private fun updateLocalitiesCache(): List<Locality> {
        val localities = localityProvider.getLocalities()
        extendableSimpleCacheManager.getCache(LOCALITIES_CACHE_NAME)?.putIfAbsent(SimpleKey.EMPTY, localities)
        return localities
    }

    private fun updateLocalitiesLettersCache(localities: List<Locality>): List<String> {
        val localitiesLetters = localities.getLocalitiesLetters()
        extendableSimpleCacheManager.getCache(LOCALITIES_LETTERS_CACHE_NAME)?.putIfAbsent(SimpleKey.EMPTY, localitiesLetters)
        return localitiesLetters
    }

    private fun updateLocalitiesNamesByLetterCache(localities: List<Locality>, localitiesLetters: List<String>) {
        val localitiesNamesByLetterCache = extendableSimpleCacheManager.getCache(LOCALITIES_NAMES_BY_LETTER_CACHE_NAME)
        localitiesLetters.forEach {
            val localityLetter = it.first()
            val localitiesNamesByLetter = localities.getLocalitiesNamesByLetter(localityLetter)
            localitiesNamesByLetterCache?.put(localityLetter, localitiesNamesByLetter)
        }
    }

    private fun updateLocalityByNameCache(localities: List<Locality>) {
        val localityByNameCache = extendableSimpleCacheManager.getCache(LOCALITY_BY_NAME_CACHE_NAME)
        localities.forEach {
            val localityName = it.name
            val localityByName = localities.getLocalityByName(localityName)
            localityByNameCache?.put(localityName, localityByName)
        }
    }
}