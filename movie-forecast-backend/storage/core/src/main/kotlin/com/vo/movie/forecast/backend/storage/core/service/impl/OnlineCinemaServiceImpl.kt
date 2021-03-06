package com.vo.movie.forecast.backend.storage.core.service.impl

import com.vo.movie.forecast.backend.storage.core.converter.toDTO
import com.vo.movie.forecast.backend.storage.core.converter.toDocument
import com.vo.movie.forecast.backend.storage.core.dao.OnlineCinemaRepository
import com.vo.movie.forecast.backend.storage.core.exception.NotFoundException
import com.vo.movie.forecast.backend.storage.core.service.OnlineCinemaService
import com.vo.movie.forecast.parser.api.online.cinema.OnlineCinemaApi
import com.vo.movie.forecast.parser.dto.online.cinema.MovieAccessDTO
import com.vo.movie.forecast.parser.dto.online.cinema.MovieInfoDTO
import com.vo.movie.forecast.parser.dto.online.cinema.OnlineCinemaDTO
import feign.FeignException
import org.springframework.stereotype.Service

@Service
class OnlineCinemaServiceImpl(private val onlineCinemaRepository: OnlineCinemaRepository,
                              private val onlineCinemaApi: OnlineCinemaApi) : OnlineCinemaService {

    override fun getMovieAccess(onlineCinema: OnlineCinemaDTO, movieInfo: MovieInfoDTO): MovieAccessDTO {
        val onlineCinemaDocument = onlineCinema.toDocument()
        val movieAccess = onlineCinemaRepository.getMovieAccess(onlineCinemaDocument, movieInfo.toDocument())
        if (movieAccess != null) return movieAccess.toDTO()
        val movieAccessDTO =
            try {
                onlineCinemaApi.getMovieAccessInfo(movieInfo, onlineCinema)
            } catch (e: FeignException) {
                if (e.status() != 404) {
                    throw e
                }
                throw movieInfo.movieNotFoundException(onlineCinema)
            } ?: throw movieInfo.movieNotFoundException(onlineCinema)
        onlineCinemaRepository.save(onlineCinemaDocument, movieAccessDTO.toDocument())
        return movieAccessDTO
    }

    private fun MovieInfoDTO.movieNotFoundException(onlineCinema: OnlineCinemaDTO): NotFoundException =
        NotFoundException("Movie $title'$originalTitle'($year) not found in online cinema $onlineCinema")
}