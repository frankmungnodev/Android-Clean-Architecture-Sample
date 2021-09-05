package com.selftaughtdev.domain.genre.usecase

import com.selftaughtdev.domain.base.usecase.CoroutineUseCase
import com.selftaughtdev.domain.DispatcherProvider
import com.selftaughtdev.domain.genre.GenreRepository
import com.selftaughtdev.domain.genre.model.MovieGenre
import com.selftaughtdev.domain.genre.model.TVGenre
import javax.inject.Inject

class TVGenresUseCase @Inject constructor(
    dispatcherProvider: DispatcherProvider,
    private val genreRepository: GenreRepository
) : CoroutineUseCase<TVGenresUseCase.Params, List<TVGenre>>(dispatcherProvider) {

    data class Params(val language: String = "en-US")

    override suspend fun provide(input: Params): List<TVGenre> = input.run {
        genreRepository.getTVGenres(language)
    }
}