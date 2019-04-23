package com.poc.moviescatalog.data.di.modules

import com.poc.moviescatalog.BuildConfig
import com.poc.moviescatalog.utils.Constants.API_BASE_URL
import com.poc.moviescatalog.data.networking.api.MoviesApi
import com.poc.moviescatalog.utils.DateJsonAdapter
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*


@Module
class RemoteModule {

    @Provides
    fun providePostApi(retrofit: Retrofit): MoviesApi {
        return retrofit.create(MoviesApi::class.java)
    }

    @Provides
    fun provideRetrofitInterface(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .client(requestInterceptor())
            .addConverterFactory(MoshiConverterFactory.create(getCustomMoshi()).withNullSerialization())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
    }

    private fun getCustomMoshi(): Moshi {
        return Moshi.Builder()
            .add(Date::class.java, DateJsonAdapter("yyyy-MM-dd"))
            .build()
    }

    /**
     * Adds interceptor to request in order to set the api key
     */
    private fun requestInterceptor(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor { chain ->
            val request = chain.request()
            val url = request.url().newBuilder().addQueryParameter("api_key", BuildConfig.API_KEY).build()
            chain.proceed(request.newBuilder().url(url).build())
        }.build()
    }
}