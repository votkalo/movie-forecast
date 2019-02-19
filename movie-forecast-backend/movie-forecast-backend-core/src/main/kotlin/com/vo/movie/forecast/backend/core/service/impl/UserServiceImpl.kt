package com.vo.movie.forecast.backend.core.service.impl

import com.vo.movie.forecast.backend.core.dao.UserRepository
import com.vo.movie.forecast.backend.core.service.UserService
import com.vo.movie.forecast.backend.data.UserInfo
import com.vo.movie.forecast.commons.data.Locality
import com.vo.movie.forecast.commons.data.Movie
import org.springframework.stereotype.Service

@Service
open class UserServiceImpl(private val userRepository: UserRepository) : UserService {

    override fun registerMovie(userId: Long, movie: Movie) = userRepository.registerMovie(userId, movie)

    override fun updateLocality(userId: Long, locality: Locality) = userRepository.updateLocality(userId, locality)

    override fun existsMovie(userId: Long, kinopoiskMovieId: Long): Boolean = userRepository.existsMovie(userId, kinopoiskMovieId)

    override fun getUsersInfoWithLocality(page: Int, size: Int): List<UserInfo> = userRepository.getUsersInfoWithLocality(page, size)
}