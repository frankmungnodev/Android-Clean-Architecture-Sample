package com.selftaughtdev.domain.movie

import com.selftaughtdev.domain.movie.model.Movie

interface MovieRepository {
    fun discoverMovies(page: Int, limit: Int, source: String): List<Movie>
}