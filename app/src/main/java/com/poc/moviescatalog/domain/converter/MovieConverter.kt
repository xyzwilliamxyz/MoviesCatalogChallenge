package com.poc.moviescatalog.domain.converter

import com.poc.moviescatalog.data.networking.response.MovieResponse
import com.poc.moviescatalog.domain.entities.Movie

object MovieConverter {

    fun fromResponse(movieResponse: MovieResponse): Movie {
        return Movie(
            movieResponse.title,
            movieResponse.poster,
            LanguageConverter.fromResponse(movieResponse.languages),
            movieResponse.overview,
            movieResponse.releaseDate,
            GenreConverter.fromResponse(movieResponse.genres)
        )
    }
}
