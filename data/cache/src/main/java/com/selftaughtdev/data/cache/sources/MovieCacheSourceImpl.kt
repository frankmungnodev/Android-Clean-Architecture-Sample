package com.selftaughtdev.data.cache.sources

import com.selftaughtdev.data.cache.mapper.MovieEntityMapper
import com.selftaughtdev.data.cache.room.dao.MovieDao
import com.selftaughtdev.data.common.movies.MovieCacheSource
import com.selftaughtdev.domain.DISCOVER_MOVIE
import com.selftaughtdev.domain.movie.model.Movie
import javax.inject.Inject

class MovieCacheSourceImpl @Inject constructor(
    private val movieDao: MovieDao,
    private var movieEntityMapper: MovieEntityMapper
) : MovieCacheSource {

    override fun insertDiscoverMovies(list: List<Movie>) {
        movieEntityMapper.source = DISCOVER_MOVIE
        list.map { movieDao.insertDiscoverMovies(movieEntityMapper.mapToDto(it)) }
    }

    override fun getDiscoverMoviesList(page: Int, limit: Int, source: String): List<Movie> {
        val skip = (page - 1) * limit
        return movieDao.getDiscoverMovies(limit, skip, source).map {
            movieEntityMapper.mapToDomain(it)
        }
    }

    override suspend fun deleteDiscoverMovies() {
        movieDao.deleteDiscoverMovies(DISCOVER_MOVIE)
    }

}