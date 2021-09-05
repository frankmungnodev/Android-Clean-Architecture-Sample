package com.selftaughtdev.data.common.movies

import com.selftaughtdev.domain.movie.model.Movie

interface MovieNetworkSource {
    fun discoverMovies(page: Int?): List<Movie>
}