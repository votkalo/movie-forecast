package com.vo.movie.forecast.backend.storage.core.updater

import com.vo.movie.forecast.backend.storage.core.configuration.BackendStorageCacheConfiguration
import com.vo.movie.forecast.backend.storage.core.dao.LocalityRepository
import com.vo.movie.forecast.backend.storage.core.util.getLocalitiesLetters
import com.vo.movie.forecast.backend.storage.core.util.getLocalitiesNamesByLetter
import com.vo.movie.forecast.backend.storage.core.util.getLocalityByName
import com.vo.movie.forecast.backend.storage.data.LocalityDTO
import com.vo.movie.forecast.backend.user.core.converter.toEntity
import com.vo.movie.forecast.commons.cache.ExtendableSimpleCacheManager
import org.springframework.cache.interceptor.SimpleKey
import org.springframework.stereotype.Component

@Component
class LocalityUpdater(private val cacheManager: ExtendableSimpleCacheManager,
                      private val localityRepository: LocalityRepository) {

    fun update(localities: List<LocalityDTO>) {
        clearCaches()
        updateLocalities(localities)
        val localitiesLetters = updateLocalitiesLetters(localities)
        updateLocalitiesNamesByLetter(localities, localitiesLetters)
        updateLocalityByName(localities)
    }

    private fun clearCaches() {
        cacheManager.clearCaches(
                BackendStorageCacheConfiguration.LOCALITIES_CACHE_NAME,
                BackendStorageCacheConfiguration.LOCALITIES_LETTERS_CACHE_NAME,
                BackendStorageCacheConfiguration.LOCALITIES_NAMES_BY_LETTER_CACHE_NAME,
                BackendStorageCacheConfiguration.LOCALITY_BY_NAME_CACHE_NAME
        )
    }

    private fun updateLocalities(localities: List<LocalityDTO>) {
        localities.forEach { localityRepository.saveLocality(it.toEntity()) }
        cacheManager.getCache(BackendStorageCacheConfiguration.LOCALITIES_CACHE_NAME)
            ?.putIfAbsent(SimpleKey.EMPTY, localities)
    }

    private fun updateLocalitiesLetters(localities: List<LocalityDTO>): List<String> {
        val localitiesLetters = localities.getLocalitiesLetters()
        cacheManager.getCache(BackendStorageCacheConfiguration.LOCALITIES_LETTERS_CACHE_NAME)
            ?.putIfAbsent(SimpleKey.EMPTY, localitiesLetters)
        return localitiesLetters
    }

    private fun updateLocalitiesNamesByLetter(localities: List<LocalityDTO>, localitiesLetters: List<String>) {
        val localitiesNamesByLetterCache =
            cacheManager.getCache(BackendStorageCacheConfiguration.LOCALITIES_NAMES_BY_LETTER_CACHE_NAME)
        localitiesLetters.forEach {
            val localityLetter = it.first()
            val localitiesNamesByLetter = localities.getLocalitiesNamesByLetter(localityLetter)
            localitiesNamesByLetterCache?.put(localityLetter, localitiesNamesByLetter)
        }
    }

    private fun updateLocalityByName(localities: List<LocalityDTO>) {
        val localityByNameCache = cacheManager.getCache(BackendStorageCacheConfiguration.LOCALITY_BY_NAME_CACHE_NAME)
        localities.forEach {
            val localityName = it.name
            val localityByName = localities.getLocalityByName(localityName)
            localityByNameCache?.put(localityName, localityByName)
        }
    }
}