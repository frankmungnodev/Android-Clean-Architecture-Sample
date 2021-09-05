package com.selftaughtdev.data.common.movies

import com.selftaughtdev.domain.movie.MovieRepository
import com.selftaughtdev.domain.movie.model.Movie
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepositoryImpl @Inject constructor(
    private val movieCacheSource: MovieCacheSource,
    private val movieNetworkSource: MovieNetworkSource
) : MovieRepository {

    override fun discoverMovies(page: Int, limit: Int, source: String): List<Movie> {
        try {
            val moviesFromNetwork = movieNetworkSource.discoverMovies(page)
            movieCacheSource.insertDiscoverMovies(moviesFromNetwork)
        } catch (e: Exception) {
            return movieCacheSource.getDiscoverMoviesList(page, limit, source)
        }

        return movieCacheSource.getDiscoverMoviesList(page, limit, source)
    }
}