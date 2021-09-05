package com.selftaughtdev.data.network.dto.model

import com.selftaughtdev.data.common.mapper.DtoModel
import com.selftaughtdev.data.common.mapper.Mapper
import com.selftaughtdev.domain.genre.model.MovieGenre
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import javax.inject.Inject

@JsonClass(generateAdapter = true)
data class MovieGenreDto(

    @Json(name = "id")
    val id: Int,

    @Json(name = "name")
    val name: String

) : DtoModel()