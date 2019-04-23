package com.poc.moviescatalog.domain.interactors

import com.poc.moviescatalog.data.networking.api.MoviesApi
import com.poc.moviescatalog.domain.converter.ChangeConverter
import com.poc.moviescatalog.domain.entities.Change
import com.poc.moviescatalog.ui.movielist.MovieListPresenter
import com.poc.moviescatalog.utils.removeAdultMovies
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class GetMoviesChanges @Inject constructor() {

    @Inject
    lateinit var moviesApi: MoviesApi

    private var subscription: Disposable? = null

    fun execute(callback: MovieListPresenter.MovieChangesApiCallback, lastDays: Int = 0) {

        var startDate: String? = null
        var endDate: String? = null

        if (lastDays != 0) {
            val sdf = SimpleDateFormat("yyyy-MM-dd")
            endDate = sdf.format(Date())
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.DAY_OF_YEAR, -lastDays)
            startDate = sdf.format(calendar.time)
        }

        subscription = moviesApi
            .getChanges(startDate, endDate)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .doOnTerminate { callback.onTerminate() }
            .subscribe(
                { result -> callback.onSuccess(ChangeConverter.fromResponse(result.results.removeAdultMovies())) },
                { callback.onError() }
            )

    }

    fun dispose() {
        subscription?.dispose()
    }
}
