package com.vo.movie.forecast.backend.user.data

import com.vo.movie.forecast.backend.storage.data.LocalityDTO

data class UserWithLocalityInfoDTO(val userId: Long, val locality: LocalityDTO)
