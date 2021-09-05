package com.selftaughtdev.themoviedb.di

import com.selftaughtdev.domain.DispatcherProvider
import com.selftaughtdev.themoviedb.helper.AndroidDispatcher
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    abstract fun bindDispatcher(androidDispatcher: AndroidDispatcher): DispatcherProvider
}