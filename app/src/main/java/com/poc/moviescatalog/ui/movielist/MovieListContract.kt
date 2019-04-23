package com.poc.moviescatalog.ui.movielist

import com.poc.moviescatalog.domain.entities.Movie
import com.poc.moviescatalog.ui.BaseContract

interface MovieListContract {

    interface Presenter: BaseContract.Presenter {
        fun loadPage(page: Int)

        fun applyFilter(filter: String)
    }

    interface View {

        fun addMovies(movies: List<Movie>)

        fun setLastCall(lastCall: Long)

        fun showLoading(status: Boolean)

        fun clearMovies()

        fun showErrorDialog()
    }
}
