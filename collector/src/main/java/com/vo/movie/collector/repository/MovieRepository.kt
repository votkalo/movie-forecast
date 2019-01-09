package com.vo.movie.collector.repository

import com.vo.movie.collector.entity.MovieEntity
import org.springframework.data.jpa.repository.JpaRepository

interface MovieRepository : JpaRepository<MovieEntity, Long>