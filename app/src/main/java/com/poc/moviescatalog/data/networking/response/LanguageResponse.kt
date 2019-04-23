package com.poc.moviescatalog.data.networking.response

import com.squareup.moshi.Json

data class LanguageResponse(val name: String,
                            @Json(name = "iso_3166_1") val iso: String?)