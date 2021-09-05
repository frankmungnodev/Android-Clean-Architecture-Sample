package com.selftaughtdev.domain.genre.usecase

import com.selftaughtdev.domain.base.usecase.CoroutineUseCase
import com.selftaughtdev.domain.DispatcherProvider
import com.selftaughtdev.domain.genre.model.MovieGenre
import com.selftaughtdev.domain.genre.GenreRepository
import javax.inject.Inject

class MovieGenresUseCase @Inject constructor(
    dispatcherProvider: DispatcherProvider,
    private val genreRepository: GenreRepository
) : CoroutineUseCase<MovieGenresUseCase.Params, List<MovieGenre>>(dispatcherProvider) {

    data class Params(val language: String = "en-US")

    override suspend fun provide(input: Params): List<MovieGenre> = input.run {
        genreRepository.getMovieGenres(language)
    }
}