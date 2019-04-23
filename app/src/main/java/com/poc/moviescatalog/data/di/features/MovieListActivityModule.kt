package com.poc.moviescatalog.data.di.features

import com.poc.moviescatalog.ui.movielist.MovieListActivity
import com.poc.moviescatalog.ui.movielist.MovieListContract
import dagger.Binds
import dagger.Module

@Module
abstract class MovieListActivityModule {

    @Binds
    internal abstract fun provideMovieListActivityView(activity: MovieListActivity): MovieListContract.View
}
