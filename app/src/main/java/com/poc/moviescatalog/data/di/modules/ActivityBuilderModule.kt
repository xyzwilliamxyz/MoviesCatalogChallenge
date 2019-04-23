package com.poc.moviescatalog.data.di.modules

import com.poc.moviescatalog.data.di.features.MovieListActivityModule
import com.poc.moviescatalog.ui.movielist.MovieListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = [MovieListActivityModule::class])
    abstract fun bindMovieListActivity(): MovieListActivity
}
