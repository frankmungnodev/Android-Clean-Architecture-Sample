package com.selftaughtdev.data.cache.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import com.selftaughtdev.data.common.mapper.DtoModel

@Entity(tableName = "movie_table", primaryKeys = ["id", "source"])
data class MovieEntity(

    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "title")
    val title: String?,

    @ColumnInfo(name = "original_title")
    val originalTitle: String?,

    @ColumnInfo(name = "poster_path")
    val posterPath: String?,

    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String?,

    @ColumnInfo(name = "overview")
    val overview: String?,

    @ColumnInfo(name = "release_date")
    val releaseDate: String?,

    @ColumnInfo(name = "rating")
    val rating: Double?,

    @ColumnInfo(name = "genres")
    val movieGenreIds: List<Int>?,

    @ColumnInfo(name = "source")
    val source: String

) : DtoModel()