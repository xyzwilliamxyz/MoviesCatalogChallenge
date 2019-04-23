package com.poc.moviescatalog.data.di.modules

import android.content.Context
import com.poc.moviescatalog.config.MoviesCatalogApplication
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

    @Provides
    fun provideApplication(app: MoviesCatalogApplication): Context = app
}
