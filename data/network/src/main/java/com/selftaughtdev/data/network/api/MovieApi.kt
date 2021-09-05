package com.selftaughtdev.data.network.api

import com.selftaughtdev.data.network.dto.response.movie.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("discover/movie")
    fun discoverMovies(@Query("page") page: Int? = 1): Call<MovieResponse>
}