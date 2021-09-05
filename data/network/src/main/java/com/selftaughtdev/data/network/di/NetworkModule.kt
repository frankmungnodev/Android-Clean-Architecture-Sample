package com.selftaughtdev.data.network.di

import com.selftaughtdev.data.common.genres.GenreNetworkSource
import com.selftaughtdev.data.common.movies.MovieNetworkSource
import com.selftaughtdev.data.network.api.GenreApi
import com.selftaughtdev.data.network.api.MovieApi
import com.selftaughtdev.data.network.retrofit.interceptors.HeaderInterceptor
import com.selftaughtdev.data.network.retrofit.RetrofitBuilder
import com.selftaughtdev.data.network.sources.GenreNetworkSourceImpl
import com.selftaughtdev.data.network.sources.MovieNetworkSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(
        retrofitBuilder: RetrofitBuilder,
        headerInterceptor: HeaderInterceptor
    ): Retrofit = retrofitBuilder.addInterceptors(headerInterceptor).build()

    @Provides
    @Singleton
    fun provideMovieApi(retrofit: Retrofit): MovieApi = retrofit.create(MovieApi::class.java)

    @Provides
    @Singleton
    fun provideGenreApi(retrofit: Retrofit): GenreApi = retrofit.create(GenreApi::class.java)

    @Provides
    @Singleton
    fun provideMovieNetworkSource(movieNetworkSource: MovieNetworkSourceImpl): MovieNetworkSource =
        movieNetworkSource

    @Provides
    @Singleton
    fun provideGenreNetworkSource(genreNetworkSource: GenreNetworkSourceImpl): GenreNetworkSource =
        genreNetworkSource
}