package com.vo.movie.forecast.backend.core.repository

import com.vo.movie.forecast.backend.core.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<UserEntity, Long>