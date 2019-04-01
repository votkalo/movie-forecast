package com.vo.movie.forecast.commons.cache

import org.springframework.cache.Cache
import org.springframework.cache.support.SimpleCacheManager

class ExtendableSimpleCacheManager : SimpleCacheManager() {

    fun registerCache(newCache: Cache) {
        val caches: MutableList<Cache> = this.loadCaches().toMutableList()
        caches.add(newCache)
        this.setCaches(caches)
        this.initializeCaches()
    }

    fun registerCaches(newCaches: Collection<Cache>) {
        val caches: MutableList<Cache> = this.loadCaches().toMutableList()
        caches.addAll(newCaches)
        this.setCaches(caches)
        this.initializeCaches()
    }

    fun clearCaches(vararg cacheNames: String) {
        cacheNames.forEach { getCache(it)?.clear() }
    }
}