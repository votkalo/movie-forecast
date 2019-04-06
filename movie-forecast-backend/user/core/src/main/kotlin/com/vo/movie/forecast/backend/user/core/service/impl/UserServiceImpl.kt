package com.vo.movie.forecast.backend.user.core.service.impl

import com.vo.movie.forecast.backend.user.core.converter.toDTO
import com.vo.movie.forecast.backend.user.core.converter.toEntity
import com.vo.movie.forecast.backend.user.core.dao.UserRepository
import com.vo.movie.forecast.backend.user.core.service.UserService
import com.vo.movie.forecast.backend.user.data.UserWithLocalityInfoDTO
import com.vo.movie.forecast.parser.dto.locality.LocalityDTO
import org.springframework.stereotype.Service

@Service
open class UserServiceImpl(private val userRepository: UserRepository) : UserService {

    override fun updateLocality(userId: Long, locality: LocalityDTO) {
        userRepository.updateLocality(userId, locality.toEntity())
    }

    override fun removeLocality(userId: Long) = userRepository.removeLocality(userId)

    override fun getUsersInfoWithLocality(page: Int, size: Int): List<UserWithLocalityInfoDTO> =
        userRepository.getUsersInfoWithLocality(page, size).map { it.toDTO() }

    override fun getUsersIds(page: Int, size: Int): List<Long> = userRepository.getUsersIds(page, size)
}