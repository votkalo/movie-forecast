package com.vo.movie.forecast.backend.core.service.impl

import com.vo.movie.forecast.backend.api.dto.Follow
import com.vo.movie.forecast.backend.core.exception.ConstraintException
import com.vo.movie.forecast.backend.core.repository.FollowRepository
import com.vo.movie.forecast.backend.core.service.FollowService
import com.vo.movie.forecast.backend.core.util.toDto
import com.vo.movie.forecast.backend.core.util.toEntity
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
open class FollowServiceImpl(private val followRepository: FollowRepository) : FollowService {

    @Transactional
    override fun registerFollow(follow: Follow): Follow {
        var entity = follow.toEntity()
        try {
            entity = followRepository.save(entity)
        } catch (e: DataIntegrityViolationException) {
            throw ConstraintException("Follow already exist")
        }
        return entity.toDto()
    }
}