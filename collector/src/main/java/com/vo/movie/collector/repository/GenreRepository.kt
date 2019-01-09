package com.vo.movie.collector.repository

import com.vo.movie.collector.entity.GenreEntity
import org.springframework.data.jpa.repository.JpaRepository

interface GenreRepository : JpaRepository<GenreEntity, Long> {

    fun findAllByNameIn(names: List<String>): List<GenreEntity>
}