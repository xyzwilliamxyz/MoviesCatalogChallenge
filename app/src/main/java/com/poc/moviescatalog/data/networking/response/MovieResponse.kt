package com.poc.moviescatalog.data.networking.response

import com.squareup.moshi.Json
import java.util.*

data class MovieResponse(val title: String,
                         @Json(name = "poster_path") val poster: String?,
                         @Json(name = "spoken_languages") val languages: List<LanguageResponse>?,
                         val overview: String?,
                         @Json(name = "release_date") val releaseDate: Date?,
                         val genres: List<GenreResponse>?)