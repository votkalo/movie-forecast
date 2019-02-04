package com.vo.movie.forecast.parser.provider.worker

import com.vo.movie.forecast.commons.dto.Locality
import com.vo.movie.forecast.parser.provider.LocalityProvider
import com.vo.movie.forecast.parser.provider.configuration.MovieForecastParserCacheConfiguration.Companion.LOCALITIES_CACHE_NAME
import com.vo.movie.forecast.parser.provider.configuration.MovieForecastParserCacheConfiguration.Companion.LOCALITIES_LETTERS_CACHE_NAME
import com.vo.movie.forecast.parser.provider.configuration.MovieForecastParserCacheConfiguration.Companion.LOCALITIES_NAMES_BY_LETTER_CACHE_NAME
import com.vo.movie.forecast.parser.provider.configuration.MovieForecastParserCacheConfiguration.Companion.LOCALITY_BY_NAME_CACHE_NAME
import com.vo.movie.forecast.parser.provider.util.getLocalitiesLetters
import com.vo.movie.forecast.parser.provider.util.getLocalitiesNamesByLetter
import com.vo.movie.forecast.parser.provider.util.getLocalityByName
import org.springframework.cache.CacheManager
import org.springframework.cache.interceptor.SimpleKey
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct


@Component
class MovieForecastParserLocalityCacheWorker(private val cacheManager: CacheManager,
                                             private val localityProvider: LocalityProvider) {

    @Scheduled(cron = "0 0 0 1 * ?")
    fun evictAllCachesAtIntervals() {
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
        val localitiesLetters = getLocalitiesLetters(localities)
        cacheManager.getCache(LOCALITIES_LETTERS_CACHE_NAME)?.putIfAbsent(SimpleKey.EMPTY, localitiesLetters)
        return localitiesLetters
    }

    private fun updateLocalitiesNamesByLetterCache(localities: List<Locality>, localitiesLetters: List<String>) {
        val localitiesNamesByLetterCache = cacheManager.getCache(LOCALITIES_NAMES_BY_LETTER_CACHE_NAME)
        localitiesLetters.forEach {
            val localityLetter = it.first()
            val localitiesNamesByLetter = getLocalitiesNamesByLetter(localities, localityLetter)
            localitiesNamesByLetterCache?.put(localityLetter, localitiesNamesByLetter)
        }
    }

    private fun updateLocalityByNameCache(localities: List<Locality>) {
        val localityByNameCache = cacheManager.getCache(LOCALITY_BY_NAME_CACHE_NAME)
        localities.forEach {
            val localityName = it.name
            val localityByName = getLocalityByName(localities, localityName)
            localityByNameCache?.put(localityName, localityByName)
        }
    }
}