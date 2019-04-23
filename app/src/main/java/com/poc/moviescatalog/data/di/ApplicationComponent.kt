package com.poc.moviescatalog.data.di

import android.app.Application
import com.poc.moviescatalog.config.MoviesCatalogApplication
import com.poc.moviescatalog.data.di.modules.ActivityBuilderModule
import com.poc.moviescatalog.data.di.modules.ApplicationModule
import com.poc.moviescatalog.data.di.modules.RemoteModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@Component(modules = [ApplicationModule::class, AndroidSupportInjectionModule::class, ActivityBuilderModule::class,
    RemoteModule::class])
interface ApplicationComponent {

    fun inject(application: MoviesCatalogApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }
}
