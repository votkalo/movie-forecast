package com.vo.movie.forecast.worker.repository

import com.vo.movie.forecast.worker.entity.MovieEntity
import org.springframework.data.jpa.repository.JpaRepository

interface MovieRepository : JpaRepository<MovieEntity, Long>