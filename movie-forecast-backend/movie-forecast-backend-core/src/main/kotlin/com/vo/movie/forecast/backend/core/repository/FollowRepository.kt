package com.vo.movie.forecast.backend.core.repository

import com.vo.movie.forecast.backend.core.entity.FollowEntity
import org.springframework.data.jpa.repository.JpaRepository

interface FollowRepository : JpaRepository<FollowEntity, Long>