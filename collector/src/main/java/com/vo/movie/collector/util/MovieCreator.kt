package com.vo.movie.collector.util

import com.vo.movie.collector.dto.Movie

private fun MutableMap<String, List<String>>.getSingleProperty(property: String): String? {
    val values: List<String> = get(property) ?: return null
    val value = values.getOrNull(0)
    if (value == null || value.isBlank()) return null
    return value
}

private fun MutableMap<String, List<String>>.getProperties(property: String): List<String> = get(property) ?: emptyList()

private fun MutableMap<String, List<String>>.getKinopoiskSingleProperty(dictionaryProperty: MovieDictionary): String? = getSingleProperty(dictionaryProperty.kinopoisk)
private fun MutableMap<String, List<String>>.getKinopoiskProperties(dictionaryProperty: MovieDictionary): List<String> = getProperties(dictionaryProperty.kinopoisk)
private fun MutableMap<String, List<String>>.getKinopoiskJoinProperty(dictionaryProperty: MovieDictionary): String? {
    val value = getProperties(dictionaryProperty.kinopoisk).joinToString()
    return if (value.isBlank()) null else value
}


fun createByKinopoiskProperties(properties: MutableMap<String, List<String>>): Movie =
        Movie(year = properties.getKinopoiskSingleProperty(MovieDictionary.YEAR)!!.toInt(),
                russianTitle = properties.getKinopoiskSingleProperty(MovieDictionary.RUSSIAN_TITLE)!!,
                alternativeTitle = properties.getKinopoiskSingleProperty(MovieDictionary.ALTERNATIVE_TITLE)!!,
                sourceURL = properties.getKinopoiskSingleProperty(MovieDictionary.SOURCE_URL)!!,
                russianPremiere = properties.getKinopoiskSingleProperty(MovieDictionary.RUSSIAN_PREMIERE)!!,
                tagline = properties.getKinopoiskJoinProperty(MovieDictionary.TAGLINE),
                worldPremiere = properties.getKinopoiskSingleProperty(MovieDictionary.WORLD_PREMIERE),
                bluRayRelease = properties.getKinopoiskSingleProperty(MovieDictionary.BLU_RAY_RELEASE),
                digitalRelease = properties.getKinopoiskSingleProperty(MovieDictionary.DIGITAL_RELEASE),
                reRelease = properties.getKinopoiskSingleProperty(MovieDictionary.RE_RELEASE),
                allowAge = properties.getKinopoiskJoinProperty(MovieDictionary.ALLOW_AGE),
                pg = properties.getKinopoiskSingleProperty(MovieDictionary.PG),
                duration = properties.getKinopoiskSingleProperty(MovieDictionary.DURATION),
                kinopoiskRating = properties.getKinopoiskSingleProperty(MovieDictionary.KINOPOISK_RATING)?.toBigDecimal(),
                imdbRating = properties.getKinopoiskSingleProperty(MovieDictionary.IMDB_RATING)?.toBigDecimal(),
                counties = properties.getKinopoiskProperties(MovieDictionary.COUNTRIES),
                genres = properties.getKinopoiskProperties(MovieDictionary.GENRES),
                posterURL = properties.getKinopoiskSingleProperty(MovieDictionary.POSTER_URL))
