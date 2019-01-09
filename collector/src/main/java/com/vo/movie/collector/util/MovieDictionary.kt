package com.vo.movie.collector.util

enum class MovieDictionary(val kinopoisk: String) {
    RUSSIAN_TITLE(kinopoisk = "русское название"),
    ALTERNATIVE_TITLE(kinopoisk = "альтернативное название"),
    YEAR(kinopoisk = "год"),
    COUNTRIES(kinopoisk = "страна"),
    TAGLINE(kinopoisk = "слоган"),
    GENRES(kinopoisk = "жанр"),
    WORLD_PREMIERE(kinopoisk = "премьера (мир)"),
    RUSSIAN_PREMIERE(kinopoisk = "премьера (РФ)"),
    BLU_RAY_RELEASE(kinopoisk = "релиз на Blu-ray"),
    DIGITAL_RELEASE(kinopoisk = "цифровой релиз"),
    RE_RELEASE(kinopoisk = "ре-релиз (РФ)"),
    ALLOW_AGE(kinopoisk = "возраст"),
    PG(kinopoisk = "рейтинг MPAA"),
    DURATION(kinopoisk = "время"),
    KINOPOISK_RATING(kinopoisk = "рейтинг кинопоиск"),
    IMDB_RATING(kinopoisk = "IMDb"),
    SOURCE_URL(kinopoisk = "url страницы"),
    POSTER_URL(kinopoisk = "url постера"),
}