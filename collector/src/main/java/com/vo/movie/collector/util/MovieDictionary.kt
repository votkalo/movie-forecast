package com.vo.movie.collector.util

enum class MovieDictionary(val kinopoisk: String) {
    RUSSIAN_TITLE(kinopoisk = "русское название"),
    ORIGINAL_TITLE(kinopoisk = "оригинальное название"),
    YEAR(kinopoisk = "год"),
    COUNTRIES(kinopoisk = "страна"),
    TAGLINE(kinopoisk = "слоган"),
    GENRES(kinopoisk = "жанр"),
    PREMIERE_WORLD(kinopoisk = "премьера (мир)"),
    PREMIERE_RUSSIAN(kinopoisk = "премьера (РФ)"),
    BLU_RAY_RELEASE(kinopoisk = "релиз на Blu-ray"),
    DIGITAL_RELEASE(kinopoisk = "цифровой релиз"),
    RE_RELEASE(kinopoisk = "ре-релиз (РФ)"),
    ALLOW_AGE(kinopoisk = "возраст"),
    PG(kinopoisk = "рейтинг MPAA"),
    DURATION(kinopoisk = "время"),
    RATING_KINOPOISK(kinopoisk = "рейтинг кинопоиск"),
    RATING_IMDB(kinopoisk = "IMDb"),
    SOURCE_URL(kinopoisk = "url страницы"),
}