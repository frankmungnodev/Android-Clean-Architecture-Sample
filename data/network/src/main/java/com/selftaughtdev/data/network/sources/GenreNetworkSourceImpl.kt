package com.selftaughtdev.data.network.sources

import com.selftaughtdev.data.common.genres.GenreNetworkSource
import com.selftaughtdev.data.network.api.GenreApi
import com.selftaughtdev.data.network.mapper.MapperFacade
import com.selftaughtdev.data.network.retrofit.executeOrThrow
import com.selftaughtdev.domain.base.model.NetworkResponse
import com.selftaughtdev.domain.genre.model.MovieGenre
import com.selftaughtdev.domain.genre.model.TVGenre
import javax.inject.Inject

class GenreNetworkSourceImpl @Inject constructor(
    private val api: GenreApi,
    private val mapperFacade: MapperFacade
) : GenreNetworkSource {

    override suspend fun getMovieGenres(language: String): List<MovieGenre> {
        return api.getMovieGenres(language)
            .executeOrThrow()
            .genreList
            .map {
                mapperFacade.movieGenreMapper.mapToDomain(it)
            }
    }

    override suspend fun getTvGenres(language: String): List<TVGenre> {
        return api.getTVGenres(language)
            .executeOrThrow()
            .genreList
            .map {
                mapperFacade.tvGenreMapper.mapToDomain(it)
            }
    }

}