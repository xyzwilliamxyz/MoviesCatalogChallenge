package com.poc.moviescatalog.domain.interactors

import com.poc.moviescatalog.data.networking.api.MoviesApi
import com.poc.moviescatalog.domain.converter.MovieConverter
import com.poc.moviescatalog.domain.entities.Change
import com.poc.moviescatalog.ui.movielist.MovieListPresenter
import com.poc.moviescatalog.utils.Constants
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GetMovieDetails @Inject constructor() {

    @Inject
    lateinit var moviesApi: MoviesApi

    private var disposables: CompositeDisposable = CompositeDisposable()

    fun execute(changes: List<Change>, page: Int, callback: MovieListPresenter.MovieDetailsApiCallback) {
        for (i in 0 until Constants.PAGE_SIZE) {
            disposables.add(moviesApi
                .getMovieDetails(changes[page * Constants.PAGE_SIZE + i].id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnTerminate { callback.onTerminate() }
                .subscribe({ result -> callback.onSuccess(MovieConverter.fromResponse(result)) }, { callback.onError() })
            )
        }
    }

    fun dispose() {
        disposables.dispose()
    }
}