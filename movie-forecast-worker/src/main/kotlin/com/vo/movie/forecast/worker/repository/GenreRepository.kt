package com.vo.movie.forecast.worker.repository

import com.vo.movie.forecast.worker.entity.GenreEntity
import org.springframework.data.jpa.repository.JpaRepository

interface GenreRepository : JpaRepository<GenreEntity, Long> {

    fun findAllByNameIn(names: List<String>): List<GenreEntity>
}