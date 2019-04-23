package com.poc.moviescatalog.domain.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Language(val name: String,
                    val iso: String?): Parcelable
