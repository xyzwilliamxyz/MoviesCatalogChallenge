package com.poc.moviescatalog.data.networking.callback

interface ApiCallback<T> {

    fun onSuccess(result: T)

    fun onError()

    fun onTerminate()
}