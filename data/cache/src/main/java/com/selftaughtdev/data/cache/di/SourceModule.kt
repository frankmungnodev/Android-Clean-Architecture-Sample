package com.selftaughtdev.data.cache.di

import com.selftaughtdev.data.cache.sources.GenreCacheSourceImpl
import com.selftaughtdev.data.cache.sources.MovieCacheSourceImpl
import com.selftaughtdev.data.common.genres.GenreCacheSource
import com.selftaughtdev.data.common.movies.MovieCacheSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SourceModule {

    @Provides
    @Singleton
    fun provideMovieCacheSource(movieCacheSource: MovieCacheSourceImpl): MovieCacheSource =
        movieCacheSource

    @Provides
    @Singleton
    fun provideGenreCacheSource(genreCacheSource: GenreCacheSourceImpl): GenreCacheSource =
        genreCacheSource
}