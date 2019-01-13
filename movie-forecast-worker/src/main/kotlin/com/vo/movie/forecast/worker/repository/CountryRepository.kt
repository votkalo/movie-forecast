package com.vo.movie.forecast.worker.repository

import com.vo.movie.forecast.worker.entity.CountryEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CountryRepository : JpaRepository<CountryEntity, Long> {

    fun findAllByNameIn(names: List<String>): List<CountryEntity>
}