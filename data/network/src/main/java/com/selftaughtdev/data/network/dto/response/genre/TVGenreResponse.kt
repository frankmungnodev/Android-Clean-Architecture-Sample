package com.selftaughtdev.data.network.dto.response.genre

import com.selftaughtdev.data.network.dto.model.TVGenreDto
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TVGenreResponse(
    @Json(name = "genres")
    val genreList: List<TVGenreDto>
)
