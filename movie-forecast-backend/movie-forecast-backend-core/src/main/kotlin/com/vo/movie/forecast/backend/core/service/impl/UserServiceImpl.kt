package com.vo.movie.forecast.backend.core.service.impl

import com.vo.movie.forecast.backend.core.dao.UserRepository
import com.vo.movie.forecast.backend.core.service.UserService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
open class UserServiceImpl(private val userRepository: UserRepository) : UserService {

    @Transactional
    override fun registerMovie(userId: Long, kinopoiskMovieId: Long) {
        return userRepository.registerMovie(userId, kinopoiskMovieId)
    }
}