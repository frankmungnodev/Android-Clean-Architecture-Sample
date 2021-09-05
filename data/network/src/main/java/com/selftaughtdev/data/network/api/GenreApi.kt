package com.selftaughtdev.data.network.api

import com.selftaughtdev.data.network.dto.response.genre.MovieGenreResponse
import com.selftaughtdev.data.network.dto.response.genre.TVGenreResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GenreApi {

    @GET("genre/movie/list")
    fun getMovieGenres(@Query("language") language: String): Call<MovieGenreResponse>

    @GET("genre/tv/list")
    fun getTVGenres(@Query("language") language: String): Call<TVGenreResponse>
}