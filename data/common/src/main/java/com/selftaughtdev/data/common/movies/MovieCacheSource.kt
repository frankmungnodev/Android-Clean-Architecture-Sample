package com.selftaughtdev.data.common.movies

import com.selftaughtdev.domain.movie.model.Movie

interface MovieCacheSource {

    fun insertDiscoverMovies(list: List<Movie>)

    fun getDiscoverMoviesList(page: Int, limit: Int, source: String): List<Movie>

    suspend fun deleteDiscoverMovies()
}