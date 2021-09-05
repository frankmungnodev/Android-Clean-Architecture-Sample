package com.selftaughtdev.data.cache.di

import android.content.Context
import androidx.room.Room
import com.selftaughtdev.data.cache.room.AppDatabase
import com.selftaughtdev.data.cache.room.dao.MovieDao
import com.selftaughtdev.data.cache.room.dao.MovieGenreDao
import com.selftaughtdev.data.cache.room.dao.TvGenreDao
import com.selftaughtdev.data.cache.sources.GenreCacheSourceImpl
import com.selftaughtdev.data.cache.sources.MovieCacheSourceImpl
import com.selftaughtdev.data.common.genres.GenreCacheSource
import com.selftaughtdev.data.common.movies.MovieCacheSource
import com.selftaughtdev.domain.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieDao(appDatabase: AppDatabase): MovieDao = appDatabase.movieDao

    @Provides
    @Singleton
    fun provideMovieGenreDao(appDatabase: AppDatabase): MovieGenreDao = appDatabase.movieGenreDao

    @Provides
    @Singleton
    fun provideTVGenreDao(appDatabase: AppDatabase): TvGenreDao = appDatabase.tvGenreDao
}