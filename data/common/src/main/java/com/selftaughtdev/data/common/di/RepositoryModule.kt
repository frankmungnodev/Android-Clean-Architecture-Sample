package com.selftaughtdev.data.common.di

import com.selftaughtdev.data.common.genres.GenreRepositoryImpl
import com.selftaughtdev.data.common.movies.MovieRepositoryImpl
import com.selftaughtdev.domain.genre.GenreRepository
import com.selftaughtdev.domain.movie.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMovieRepository(repository: MovieRepositoryImpl): MovieRepository = repository

    @Provides
    @Singleton
    fun provideGenreRepository(repository: GenreRepositoryImpl): GenreRepository = repository

}