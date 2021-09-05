package com.selftaughtdev.data.network.sources

import com.selftaughtdev.data.common.movies.MovieNetworkSource
import com.selftaughtdev.data.network.api.MovieApi
import com.selftaughtdev.data.network.mapper.MapperFacade
import com.selftaughtdev.data.network.retrofit.executeOrThrow
import com.selftaughtdev.domain.base.model.NetworkResponse
import com.selftaughtdev.domain.movie.model.Movie
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieNetworkSourceImpl @Inject constructor(
    private val api: MovieApi,
    private val mapperFacade: MapperFacade
) : MovieNetworkSource {

    override fun discoverMovies(page: Int?): List<Movie> {
        return api.discoverMovies(page)
            .executeOrThrow()
            .data
            .map {
                mapperFacade.movieMapper.mapToDomain(it)
            }
    }
}