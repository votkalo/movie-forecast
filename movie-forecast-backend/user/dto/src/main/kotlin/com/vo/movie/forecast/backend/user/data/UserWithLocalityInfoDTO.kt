package com.vo.movie.forecast.backend.user.data

import com.vo.movie.forecast.parser.dto.locality.LocalityDTO

data class UserWithLocalityInfoDTO(val userId: Long, val locality: LocalityDTO)
