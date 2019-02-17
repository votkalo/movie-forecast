package com.vo.movie.forecast.backend.feign.bot

import com.vo.movie.forecast.backend.api.bot.UserApi
import com.vo.movie.forecast.commons.data.Locality
import com.vo.movie.forecast.commons.data.Movie
import feign.Param
import feign.RequestLine

interface UserApiFeign : UserApi {

    @RequestLine("PUT /users/{userId}/registerMovie")
    override fun registerMovie(@Param("userId") userId: Long, movie: Movie)

    @RequestLine("PUT /users/{userId}/updateLocality")
    override fun updateLocality(@Param("userId") userId: Long, locality: Locality)

    @RequestLine("GET /users/{userId}/movies/{kinopoiskMovieId}/exists")
    override fun existsMovie(@Param("userId") userId: Long, @Param("kinopoiskMovieId") kinopoiskMovieId: Long): Boolean
}
