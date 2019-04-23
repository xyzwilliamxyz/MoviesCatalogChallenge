package com.poc.moviescatalog.ui.movielist

import com.poc.moviescatalog.domain.entities.Movie

interface OnMovieClickListener {
        fun openMovie(movie: Movie)
    }