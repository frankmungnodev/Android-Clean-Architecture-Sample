package com.selftaughtdev.data.network.dto.model

import com.selftaughtdev.data.common.mapper.DtoModel
import com.selftaughtdev.data.common.mapper.Mapper
import com.selftaughtdev.domain.movie.model.Movie
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import javax.inject.Inject

@JsonClass(generateAdapter = true)
data class MovieDto(

    @Json(name = "id")
    val id: Int,

    @Json(name = "title")
    val title: String?,

    @Json(name = "original_title")
    val originalTitle: String?,

    @Json(name = "poster_path")
    val posterPath: String?,

    @Json(name = "backdrop_path")
    val backdropPath: String?,

    @Json(name = "overview")
    val overview: String?,

    @Json(name = "release_date")
    val releaseDate: String?,

    @Json(name = "vote_average")
    val rating: Double?,

    @Json(name = "genre_ids")
    val movieGenresIds: List<Int>?
) : DtoModel()