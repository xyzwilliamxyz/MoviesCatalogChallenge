package com.poc.moviescatalog.utils

import com.poc.moviescatalog.data.networking.response.ChangeResponse
import com.poc.moviescatalog.domain.entities.Genre
import com.poc.moviescatalog.domain.entities.Language

/**
 * if adult property is null or true, ignore it in the new list
 */
fun List<ChangeResponse>.removeAdultMovies(): List<ChangeResponse> {
    val nonAdultMovies = mutableListOf<ChangeResponse>()
    forEach { if (it.adult != null && it.adult == false) nonAdultMovies.add(it) }
    return nonAdultMovies
}

fun List<Genre>?.getGenreStringList(): String {
    if (this == null) return ""
    var string = ""
    forEachIndexed { index, element ->
        if (index != 0) string += ", "
        string += element.name
    }
    return string
}

fun List<Language>?.getLanguageStringList(): String {
    if (this == null) return ""
    var string = ""
    forEachIndexed { index, element ->
        if (index != 0) string += ", "
        string += element.name
    }
    return string
}