package com.poc.moviescatalog.domain.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Change(val id: Int,
                  var adult: Boolean?) : Parcelable
