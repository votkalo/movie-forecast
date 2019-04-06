package com.vo.movie.forecast.backend.user.feign

import com.vo.movie.forecast.backend.user.api.UserApi
import com.vo.movie.forecast.backend.user.data.UserWithLocalityInfoDTO
import com.vo.movie.forecast.parser.dto.locality.LocalityDTO
import feign.Param
import feign.RequestLine

interface UserApiFeign : UserApi {

    @RequestLine("PUT /users/{userId}/locality")
    override fun updateLocality(@Param("userId") userId: Long, locality: LocalityDTO)

    @RequestLine("DELETE /users/{userId}/locality")
    override fun removeLocality(@Param("userId") userId: Long)

    @RequestLine("GET /users?page={page}&size={size}")
    override fun getUsersInfoWithLocality(@Param("page") page: Int, @Param("size") size: Int): List<UserWithLocalityInfoDTO>

    @RequestLine("GET /users/ids?page={page}&size={size}")
    override fun getUsersIds(@Param("page") page: Int, @Param("size") size: Int): List<Long>

}
