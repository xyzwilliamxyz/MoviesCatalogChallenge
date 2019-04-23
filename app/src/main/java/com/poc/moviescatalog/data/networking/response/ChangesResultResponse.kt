package com.poc.moviescatalog.data.networking.response

import com.google.gson.annotations.SerializedName

data class ChangesResultResponse(val results: List<ChangeResponse>,
                                 val page: Int,
                                 @SerializedName("total_pages") val totalPages: Int,
                                 @SerializedName("total_results") val totalResults: Int)