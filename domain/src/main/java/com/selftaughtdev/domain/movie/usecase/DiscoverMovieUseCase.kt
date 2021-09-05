package com.selftaughtdev.domain.movie.usecase

import com.selftaughtdev.domain.DISCOVER_MOVIE
import com.selftaughtdev.domain.DispatcherProvider
import com.selftaughtdev.domain.base.usecase.CoroutineUseCase
import com.selftaughtdev.domain.movie.MovieRepository
import com.selftaughtdev.domain.movie.model.Movie
import javax.inject.Inject

class DiscoverMovieUseCase @Inject constructor(
    dispatcherProvider: DispatcherProvider,
    private val movieRepository: MovieRepository
) : CoroutineUseCase<DiscoverMovieUseCase.Params, List<Movie>>(dispatcherProvider) {

    data class Params(val page: Int = 1, val limit: Int = 50, val source: String = DISCOVER_MOVIE)

    override suspend fun provide(input: Params): List<Movie> = input.run {
        movieRepository.discoverMovies(page, limit, source)
    }
}