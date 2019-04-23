package com.poc.moviescatalog.utils

object Constants {

    const val API_BASE_URL = "https://api.themoviedb.org/3/"
    const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500/"

    const val PAGE_SIZE = 36

    /**
     * MovieDb api has request limitations.
     * Documentation:"https://developers.themoviedb.org/3/getting-started/request-rate-limiting"
     *
     * Request Rate Limiting
     * We do enforce a small amount of rate limiting. Our current limits are 40 requests every 10 seconds and are limited
     * by IP address, not API key. You can think of this is being burstable to 40 in a single second, or as an average
     * of 4 requests/second. The timer will reset 10 seconds from your first request within the current 10 second
     * "bucket". This means that if you trigger the limit you will have to wait up to 9 seconds before the timer resets
     * but depending where you are within the 10 second window, it could be the very next second.
     */
    const val LOADING_MORE_REQUEST_DELAY = 11000L
}