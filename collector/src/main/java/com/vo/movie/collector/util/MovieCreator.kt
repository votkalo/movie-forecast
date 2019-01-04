package com.vo.movie.collector.util

import com.vo.movie.collector.dto.Movie

private fun MutableMap<String, List<String>>.getSingleProperty(property: String): String? {
    val values: List<String> = get(property) ?: return null
    return values.getOrNull(0)
}

private fun MutableMap<String, List<String>>.getProperties(property: String): List<String> = get(property) ?: emptyList()

private fun MutableMap<String, List<String>>.getKinopoiskSingleProperty(dictionaryProperty: MovieDictionary): String? = getSingleProperty(dictionaryProperty.kinopoisk)
private fun MutableMap<String, List<String>>.getKinopoiskProperties(dictionaryProperty: MovieDictionary): List<String> = getProperties(dictionaryProperty.kinopoisk)
private fun MutableMap<String, List<String>>.getKinopoiskJoinProperty(dictionaryProperty: MovieDictionary): String? = getProperties(dictionaryProperty.kinopoisk).joinToString()


fun createByKinopoiskProperties(properties: MutableMap<String, List<String>>): Movie =
    Movie(russianTitle = properties.getKinopoiskSingleProperty(MovieDictionary.RUSSIAN_TITLE)!!,
        originalTitle = properties.getKinopoiskSingleProperty(MovieDictionary.ORIGINAL_TITLE)!!,
        year = properties.getKinopoiskSingleProperty(MovieDictionary.YEAR)!!.toInt(),
        counties = properties.getKinopoiskProperties(MovieDictionary.COUNTRIES),
        genres = properties.getKinopoiskProperties(MovieDictionary.GENRES),
        premiereRussian = properties.getKinopoiskSingleProperty(MovieDictionary.PREMIERE_RUSSIAN)!!,
        tagline = properties.getKinopoiskJoinProperty(MovieDictionary.TAGLINE),
        premiereWorld = properties.getKinopoiskSingleProperty(MovieDictionary.PREMIERE_WORLD),
        bluRayRelease = properties.getKinopoiskSingleProperty(MovieDictionary.BLU_RAY_RELEASE),
        digitalRelease = properties.getKinopoiskSingleProperty(MovieDictionary.DIGITAL_RELEASE),
        reRelease = properties.getKinopoiskSingleProperty(MovieDictionary.RE_RELEASE),
        allowAge = properties.getKinopoiskJoinProperty(MovieDictionary.ALLOW_AGE),
        pg = properties.getKinopoiskSingleProperty(MovieDictionary.PG),
        duration = properties.getKinopoiskSingleProperty(MovieDictionary.DURATION),
        ratingKinopoisk = properties.getKinopoiskSingleProperty(MovieDictionary.RATING_KINOPOISK)?.toDouble(),
        ratingIMDB = properties.getKinopoiskSingleProperty(MovieDictionary.RATING_IMDB)?.toDouble(),
        sourceURL = properties.getKinopoiskSingleProperty(MovieDictionary.SOURCE_URL))
