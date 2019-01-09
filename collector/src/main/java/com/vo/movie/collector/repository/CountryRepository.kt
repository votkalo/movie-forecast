package com.vo.movie.collector.repository

import com.vo.movie.collector.entity.CountryEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CountryRepository : JpaRepository<CountryEntity, Long> {

    fun findAllByNameIn(names: List<String>): List<CountryEntity>
}