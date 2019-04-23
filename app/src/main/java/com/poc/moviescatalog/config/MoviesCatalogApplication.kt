package com.poc.moviescatalog.config

import android.app.Activity
import android.app.Application
import com.poc.moviescatalog.data.di.ApplicationComponent
import com.poc.moviescatalog.data.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class MoviesCatalogApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var activityInjector : DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector

    override fun onCreate() {
        super.onCreate()

        val appComponent = DaggerApplicationComponent.builder()
            .application(this)
            .build()

        appComponent.inject(this)
    }
}