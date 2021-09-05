package com.selftaughtdev.data.cache.sources

import com.selftaughtdev.data.cache.mapper.MovieGenreEntityMapper
import com.selftaughtdev.data.cache.mapper.TVGenreEntityMapper
import com.selftaughtdev.data.cache.room.dao.MovieGenreDao
import com.selftaughtdev.data.cache.room.dao.TvGenreDao
import com.selftaughtdev.data.common.genres.GenreCacheSource
import com.selftaughtdev.domain.genre.model.MovieGenre
import com.selftaughtdev.domain.genre.model.TVGenre
import javax.inject.Inject

class GenreCacheSourceImpl @Inject constructor(
    private val movieGenreDao: MovieGenreDao,
    private val tvGenreDao: TvGenreDao,
    private val movieGenreEntityMapper: MovieGenreEntityMapper,
    private val tvGenreEntityMapper: TVGenreEntityMapper
) : GenreCacheSource {

    override fun insertMovieGenreList(genres: List<MovieGenre>) {
        movieGenreDao.insertMovieGenres(genres.map { movieGenreEntityMapper.mapToDto(it) })
    }

    override fun getMovieGenreList(): List<MovieGenre> {
        return movieGenreDao.getMovieGenres().map { movieGenreEntityMapper.mapToDomain(it) }
    }

    override suspend fun deleteMovieGenre() {
        movieGenreDao.deleteMovieGenres()
    }

    override fun insertTVGenreList(genres: List<TVGenre>) {
        tvGenreDao.insertTVGenres(genres.map { tvGenreEntityMapper.mapToDto(it) })
    }

    override fun getTVGenreList(): List<TVGenre> {
        return tvGenreDao.getTVGenres().map { tvGenreEntityMapper.mapToDomain(it) }
    }

    override suspend fun deleteTVGenre() {
        tvGenreDao.deleteTVGenres()
    }
}