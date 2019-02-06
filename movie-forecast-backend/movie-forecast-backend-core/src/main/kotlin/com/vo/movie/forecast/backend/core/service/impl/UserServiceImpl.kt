package com.vo.movie.forecast.backend.core.service.impl

import com.vo.movie.forecast.backend.core.dao.UserRepository
import com.vo.movie.forecast.backend.core.service.UserService
import com.vo.movie.forecast.commons.dto.Locality
import com.vo.movie.forecast.commons.dto.Movie
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
open class UserServiceImpl(private val userRepository: UserRepository) : UserService {

    @Transactional
    override fun registerMovie(userId: Long, movie: Movie) = userRepository.registerMovie(userId, movie)

    @Transactional
    override fun updateLocality(userId: Long, locality: Locality) = userRepository.updateLocality(userId, locality)

    @Transactional(readOnly = true)
    override fun existsMovie(userId: Long, kinopoiskMovieId: Long): Boolean = userRepository.existsMovie(userId, kinopoiskMovieId)
}