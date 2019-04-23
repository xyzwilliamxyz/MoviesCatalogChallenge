package com.poc.moviescatalog.domain.converter

import com.poc.moviescatalog.data.networking.response.GenreResponse
import com.poc.moviescatalog.domain.entities.Genre

object GenreConverter {

    fun fromResponse(genreResponse: GenreResponse): Genre {
        return Genre(genreResponse.id, genreResponse.name)
    }

    fun fromResponse(genreResponse: List<GenreResponse>?): List<Genre> {
        val genres = mutableListOf<Genre>()
        genreResponse?.forEach {
            genres.add(fromResponse(it))
        }
        return genres
    }
}
