package com.selftaughtdev.data.common.genres

import com.selftaughtdev.domain.genre.model.MovieGenre
import com.selftaughtdev.domain.genre.model.TVGenre

interface GenreCacheSource {

    fun insertMovieGenreList(genres: List<MovieGenre>)

    fun getMovieGenreList(): List<MovieGenre>

    suspend fun deleteMovieGenre()

    fun insertTVGenreList(genres: List<TVGenre>)

    fun getTVGenreList(): List<TVGenre>

    suspend fun deleteTVGenre()
}