package com.vo.movie.forecast.bot.message.movie

import com.vo.movie.forecast.parser.dto.movie.MovieDTO

interface MovieMessageCreator {

    fun createFullMovieInfo(movie: MovieDTO): String

    fun createMovieTitle(movie: MovieDTO): String

    fun createMovieTitle(title: String, year: String?): String

    fun createMovieTitleForButton(movie: MovieDTO): String

    fun createShortMovieDescription(movie: MovieDTO): String
}