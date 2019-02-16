package com.vo.movie.forecast.parser.provider.worker

import com.vo.movie.forecast.commons.data.Locality
import com.vo.movie.forecast.parser.provider.LocalityProvider
import com.vo.movie.forecast.parser.provider.configuration.CacheConfiguration.Companion.LOCALITIES_CACHE_NAME
import com.vo.movie.forecast.parser.provider.configuration.CacheConfiguration.Companion.LOCALITIES_LETTERS_CACHE_NAME
import com.vo.movie.forecast.parser.provider.configuration.CacheConfiguration.Companion.LOCALITIES_NAMES_BY_LETTER_CACHE_NAME
import com.vo.movie.forecast.parser.provider.configuration.CacheConfiguration.Companion.LOCALITY_BY_NAME_CACHE_NAME
import com.vo.movie.forecast.parser.provider.configuration.CacheConfiguration.Companion.LOCALITY_CACHE_MANAGER
import com.vo.movie.forecast.parser.provider.util.getLocalitiesLetters
import com.vo.movie.forecast.parser.provider.util.getLocalitiesNamesByLetter
import com.vo.movie.forecast.parser.provider.util.getLocalityByName
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.cache.CacheManager
import org.springframework.cache.interceptor.SimpleKey
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct


@Component
class LocalityCacheWorker(@Qualifier(LOCALITY_CACHE_MANAGER) private val cacheManager: CacheManager,
                          private val localityProvider: LocalityProvider) {

    @Scheduled(cron = "0 0 0 1 * ?", zone = "Europe/Minsk")
    fun cleanUpdateCaches() {
        cacheManager.cacheNames.forEach { cacheManager.getCache(it)?.clear() }
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
        cacheManager.getCache(LOCALITIES_CACHE_NAME)?.putIfAbsent(SimpleKey.EMPTY, localities)
        return localities
    }

    private fun updateLocalitiesLettersCache(localities: List<Locality>): List<String> {
        val localitiesLetters = localities.getLocalitiesLetters()
        cacheManager.getCache(LOCALITIES_LETTERS_CACHE_NAME)?.putIfAbsent(SimpleKey.EMPTY, localitiesLetters)
        return localitiesLetters
    }

    private fun updateLocalitiesNamesByLetterCache(localities: List<Locality>, localitiesLetters: List<String>) {
        val localitiesNamesByLetterCache = cacheManager.getCache(LOCALITIES_NAMES_BY_LETTER_CACHE_NAME)
        localitiesLetters.forEach {
            val localityLetter = it.first()
            val localitiesNamesByLetter = localities.getLocalitiesNamesByLetter(localityLetter)
            localitiesNamesByLetterCache?.put(localityLetter, localitiesNamesByLetter)
        }
    }

    private fun updateLocalityByNameCache(localities: List<Locality>) {
        val localityByNameCache = cacheManager.getCache(LOCALITY_BY_NAME_CACHE_NAME)
        localities.forEach {
            val localityName = it.name
            val localityByName = localities.getLocalityByName(localityName)
            localityByNameCache?.put(localityName, localityByName)
        }
    }
}