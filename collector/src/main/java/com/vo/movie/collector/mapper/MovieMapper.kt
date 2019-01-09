package com.vo.movie.collector.mapper

import com.vo.movie.collector.dto.Movie
import com.vo.movie.collector.entity.MovieEntity

interface MovieMapper {

    fun mapToMovieEntity(movie: Movie): MovieEntity
}