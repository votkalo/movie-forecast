package com.vo.movie.forecast.backend.user.core.service.impl

import com.vo.movie.forecast.backend.user.core.dao.UserRepository
import com.vo.movie.forecast.backend.user.core.service.UserService
import com.vo.movie.forecast.backend.user.data.UserInfo
import com.vo.movie.forecast.commons.data.Locality
import org.springframework.stereotype.Service

@Service
open class UserServiceImpl(private val userRepository: UserRepository) : UserService {

    override fun updateLocality(userId: Long, locality: Locality) = userRepository.updateLocality(userId, locality)

    override fun removeLocality(userId: Long) = userRepository.removeLocality(userId)

    override fun getUsersInfoWithLocality(page: Int, size: Int): List<UserInfo> = userRepository.getUsersInfoWithLocality(page, size)

    override fun getUsersIds(page: Int, size: Int): List<Long> = userRepository.getUsersIds(page, size)
}