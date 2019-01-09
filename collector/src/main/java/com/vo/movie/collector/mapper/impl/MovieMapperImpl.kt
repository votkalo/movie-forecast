package com.vo.movie.collector.mapper.impl

import com.vo.movie.collector.dto.Movie
import com.vo.movie.collector.entity.MovieEntity
import com.vo.movie.collector.mapper.MovieMapper
import com.vo.movie.collector.repository.CountryRepository
import com.vo.movie.collector.repository.GenreRepository
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@Service
class MovieMapperImpl(private val countryRepository: CountryRepository,
                      private val genreRepository: GenreRepository) : MovieMapper {

    override fun mapToMovieEntity(movie: Movie): MovieEntity {
        val movieEntity = MovieEntity(year = movie.year,
                russianTitle = movie.russianTitle,
                alternativeTitle = movie.alternativeTitle,
                russianPremiere = mapToLocalDate(movie.russianPremiere)!!,
                worldPremiere = mapToLocalDate(movie.worldPremiere),
                tagline = movie.tagline,
                bluRayRelease = mapToLocalDate(movie.bluRayRelease),
                digitalRelease = mapToLocalDate(movie.digitalRelease),
                reRelease = mapToLocalDate(movie.reRelease),
                allowAge = movie.allowAge,
                pg = movie.pg,
                duration = movie.duration,
                kinopoiskRating = movie.kinopoiskRating,
                imdbRating = movie.imdbRating,
                sourceURL = movie.sourceURL,
                posterURL = movie.posterURL)
        movieEntity.counties.addAll(countryRepository.findAllByNameIn(movie.counties))
        movieEntity.genres.addAll(genreRepository.findAllByNameIn(movie.genres))
        return movieEntity
    }

    private fun mapToLocalDate(date: String?): LocalDate? {
        if (date == null) return null
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("d MMMM yyyy", Locale("ru")))
    }
}
