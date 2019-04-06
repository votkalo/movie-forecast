package com.vo.movie.forecast.backend.storage.core.service.impl

import com.vo.movie.forecast.backend.storage.core.configuration.BackendStorageCacheConfiguration.Companion.LOCALITIES_CACHE_NAME
import com.vo.movie.forecast.backend.storage.core.configuration.BackendStorageCacheConfiguration.Companion.LOCALITIES_LETTERS_CACHE_NAME
import com.vo.movie.forecast.backend.storage.core.configuration.BackendStorageCacheConfiguration.Companion.LOCALITIES_NAMES_BY_LETTER_CACHE_NAME
import com.vo.movie.forecast.backend.storage.core.configuration.BackendStorageCacheConfiguration.Companion.LOCALITY_BY_NAME_CACHE_NAME
import com.vo.movie.forecast.backend.storage.core.converter.toDTO
import com.vo.movie.forecast.backend.storage.core.dao.LocalityRepository
import com.vo.movie.forecast.backend.storage.core.service.LocalityService
import com.vo.movie.forecast.backend.storage.core.updater.LocalityUpdater
import com.vo.movie.forecast.backend.storage.core.util.getLocalitiesLetters
import com.vo.movie.forecast.backend.storage.core.util.getLocalitiesNamesByLetter
import com.vo.movie.forecast.backend.storage.core.util.getLocalityByName
import com.vo.movie.forecast.parser.api.locality.LocalityApi
import com.vo.movie.forecast.parser.dto.locality.LocalityDTO
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
open class LocalityServiceImpl(private val localityRepository: LocalityRepository,
                               private val localityApi: LocalityApi,
                               private val localityUpdater: LocalityUpdater) : LocalityService {

    @Cacheable(cacheNames = [LOCALITIES_CACHE_NAME])
    override fun getLocalities(): List<LocalityDTO> {
        val localities = localityRepository.getLocalities()
        if (localities.isNotEmpty()) return localities.map { it.toDTO() }
        val newLocalities = localityApi.getLocalities()
        localityUpdater.update(newLocalities)
        return newLocalities
    }

    @Cacheable(cacheNames = [LOCALITIES_LETTERS_CACHE_NAME])
    override fun getLocalitiesLetters(): List<String> {
        val letters = localityRepository.getLocalitiesLetters()
        if (letters.isNotEmpty()) return letters
        return getLocalities().getLocalitiesLetters()
    }

    @Cacheable(cacheNames = [LOCALITIES_NAMES_BY_LETTER_CACHE_NAME])
    override fun getLocalitiesNamesByLetter(letter: Char): List<String> {
        val names = localityRepository.getLocalitiesNamesByLetter(letter)
        if (names.isNotEmpty()) return names
        return getLocalities().getLocalitiesNamesByLetter(letter)
    }

    @Cacheable(cacheNames = [LOCALITY_BY_NAME_CACHE_NAME])
    override fun getLocalityByName(name: String): LocalityDTO =
        localityRepository.getLocalityByName(name)?.toDTO() ?: getLocalities().getLocalityByName(name)
}