package com.selftaughtdev.data.common.genres

import com.selftaughtdev.domain.genre.model.MovieGenre
import com.selftaughtdev.domain.genre.model.TVGenre

interface GenreNetworkSource {
    suspend fun getMovieGenres(language: String): List<MovieGenre>
    suspend fun getTvGenres(language: String): List<TVGenre>
}