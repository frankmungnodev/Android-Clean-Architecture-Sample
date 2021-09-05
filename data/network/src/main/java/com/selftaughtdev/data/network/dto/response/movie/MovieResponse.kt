package com.selftaughtdev.data.network.dto.response.movie

import com.selftaughtdev.data.network.dto.model.MovieDto
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieResponse(

    @Json(name = "page")
    val page: Int,

    @Json(name = "total_pages")
    val totalPage: Int,

    @Json(name = "results")
    val data: List<MovieDto>
)
