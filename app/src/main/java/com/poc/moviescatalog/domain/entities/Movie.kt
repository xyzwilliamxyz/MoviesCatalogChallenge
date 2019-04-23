package com.poc.moviescatalog.domain.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Movie(val title: String,
                 val poster: String?,
                 val languages: List<Language>?,
                 val overview: String?,
                 val releaseDate: Date?,
                 val genres: List<Genre>?): Parcelable
