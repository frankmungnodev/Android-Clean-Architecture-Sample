package com.selftaughtdev.data.common.genres

import com.selftaughtdev.domain.exception.NetworkException
import com.selftaughtdev.domain.genre.GenreRepository
import com.selftaughtdev.domain.genre.model.MovieGenre
import com.selftaughtdev.domain.genre.model.TVGenre
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GenreRepositoryImpl @Inject constructor(
    private val genreCacheSource: GenreCacheSource,
    private val genreNetworkSource: GenreNetworkSource
) : GenreRepository {

    override suspend fun getMovieGenres(language: String): List<MovieGenre> {
        try {
            val movieGenre = genreNetworkSource.getMovieGenres(language)
            genreCacheSource.insertMovieGenreList(movieGenre)
        } catch (e: Exception) {
            val cacheSource = genreCacheSource.getMovieGenreList()
            if (cacheSource.isEmpty()) throw e
            return cacheSource
        }

        return genreCacheSource.getMovieGenreList()
    }

    override suspend fun getTVGenres(language: String): List<TVGenre> {
        try {
            val tvGenreList = genreNetworkSource.getTvGenres(language)
            genreCacheSource.insertTVGenreList(tvGenreList)
        } catch (e: Exception) {
            val cacheSource = genreCacheSource.getTVGenreList()
            if (cacheSource.isEmpty()) throw e
            return cacheSource
        }

        return genreCacheSource.getTVGenreList()
    }
}