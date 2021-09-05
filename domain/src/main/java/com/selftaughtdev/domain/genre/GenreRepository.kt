package com.selftaughtdev.domain.genre

import com.selftaughtdev.domain.genre.model.MovieGenre
import com.selftaughtdev.domain.genre.model.TVGenre

interface GenreRepository {

    suspend fun getMovieGenres(language: String): List<MovieGenre>

    suspend fun getTVGenres(language: String): List<TVGenre>
}