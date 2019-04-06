package com.vo.movie.forecast.backend.storage.core.updater

import com.vo.movie.forecast.backend.storage.core.configuration.BackendStorageCacheConfiguration
import com.vo.movie.forecast.backend.storage.core.converter.toEntity
import com.vo.movie.forecast.backend.storage.core.dao.LocalityRepository
import com.vo.movie.forecast.commons.cache.ExtendableSimpleCacheManager
import com.vo.movie.forecast.parser.dto.locality.LocalityDTO
import org.springframework.stereotype.Component

@Component
class LocalityUpdater(private val cacheManager: ExtendableSimpleCacheManager,
                      private val localityRepository: LocalityRepository) {

    fun update(localities: List<LocalityDTO>) {
        updateLocalities(localities)
        clearCaches()
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
    }
}