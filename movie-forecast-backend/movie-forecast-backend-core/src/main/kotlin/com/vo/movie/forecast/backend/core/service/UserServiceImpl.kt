package com.vo.movie.forecast.backend.core.service

import com.vo.movie.forecast.backend.api.dto.User
import com.vo.movie.forecast.backend.core.repository.UserRepository
import com.vo.movie.forecast.backend.core.util.toDto
import com.vo.movie.forecast.backend.core.util.toEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
open class UserServiceImpl(private val userRepository: UserRepository) : UserService {

    @Transactional
    override fun registerUser(user: User): User {
        val userOptional = userRepository.findById(user.userId)
        if (!userOptional.isPresent) {
            val entity = user.toEntity()
            return userRepository.save(entity).toDto()
        }

        if (userOptional.isPresent && userOptional.get().chatId != user.chatId) {
            val entity = userOptional.get()
            entity.chatId = user.chatId
            return userRepository.save(entity).toDto()
        }
        return user
    }

    @Transactional(readOnly = true)
    override fun isUserExist(userId: Long): Boolean = userRepository.existsById(userId)
}