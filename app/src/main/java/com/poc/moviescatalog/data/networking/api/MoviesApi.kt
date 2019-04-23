package com.poc.moviescatalog.data.networking.api

import com.poc.moviescatalog.data.networking.response.ChangesResultResponse
import com.poc.moviescatalog.data.networking.response.MovieResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {

    @GET("movie/changes")
    fun getChanges(@Query("start_date") startDate: String?,
                   @Query("end_date") endDate: String?): Observable<ChangesResultResponse>

    @GET("movie/{movieId}")
    fun getMovieDetails(@Path("movieId") movieId: Int): Observable<MovieResponse>
}