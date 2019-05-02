package com.poc.moviescatalog.ui.movielist

import android.app.Activity
import android.os.Handler
import com.poc.moviescatalog.R
import com.poc.moviescatalog.data.networking.callback.ApiCallback
import com.poc.moviescatalog.domain.entities.Change
import com.poc.moviescatalog.domain.entities.Movie
import com.poc.moviescatalog.domain.interactors.GetMovieDetails
import com.poc.moviescatalog.domain.interactors.GetMoviesChanges
import com.poc.moviescatalog.utils.Constants
import javax.inject.Inject

class MovieListPresenter @Inject constructor(private val view: MovieListContract.View):
    MovieListContract.Presenter {

    @Inject
    lateinit var getMoviesChanges: GetMoviesChanges

    @Inject
    lateinit var getMovieDetails: GetMovieDetails

    lateinit var changes: List<Change>

    private var lastCall: Long = 0

    override fun onStart() {
        view.showLoading(true)
        getMoviesChanges.execute(MovieChangesApiCallback())
    }

    override fun loadPage(page: Int) {
        val differenceInMilliseconds = System.currentTimeMillis() - lastCall

        view.showLoading(true)
        if (differenceInMilliseconds > Constants.LOADING_MORE_REQUEST_DELAY) {
            lastCall = System.currentTimeMillis()
            getMovieDetails.execute(changes, page, MovieDetailsApiCallback())
        } else {
            Handler().postDelayed({
                lastCall = System.currentTimeMillis()
                getMovieDetails.execute(changes, page, MovieDetailsApiCallback())
            }, Constants.LOADING_MORE_REQUEST_DELAY - differenceInMilliseconds)
        }
    }

    override fun applyFilter(filter: String) {
        view.showLoading(true)
        getMoviesChanges.execute(MovieChangesApiCallback(), filter.toInt())
    }

    override fun onFinish() {
        getMoviesChanges.dispose()
        getMovieDetails.dispose()
    }

    inner class MovieChangesApiCallback: ApiCallback<List<Change>> {
        override fun onSuccess(result: List<Change>) {
            changes = result
            view.clearMovies()
            loadPage(0)
        }

        override fun onError() {
            view.showErrorDialog()
        }

        override fun onTerminate() {
        }
    }

    inner class MovieDetailsApiCallback: ApiCallback<Movie> {

        private var finished = 0
        private val movies = mutableListOf<Movie>()

        override fun onSuccess(result: Movie) {
            addMovie(result)
        }

        override fun onError() {
            addMovie(Movie((view as Activity).getString(R.string.movie_without_data),
                null, emptyList(), "", null, emptyList()))
        }

        override fun onTerminate() {
        }
        
        private fun addMovie(movie: Movie) {
            finished++
            movies.add(movie)
            if (finished == Constants.PAGE_SIZE) {
                view.showLoading(false)
                view.setLastCall(System.currentTimeMillis())
                view.addMovies(movies)
            }
        }
    }
}
