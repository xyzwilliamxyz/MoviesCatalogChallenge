package com.poc.moviescatalog.ui

interface BaseContract {

    interface Presenter {
        fun onStart()

        fun onFinish()
    }
}