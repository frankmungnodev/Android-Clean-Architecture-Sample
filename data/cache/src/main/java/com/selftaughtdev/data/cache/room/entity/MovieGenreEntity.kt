package com.selftaughtdev.data.cache.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.selftaughtdev.data.common.mapper.DtoModel

@Entity(tableName = "movie_genre")
data class MovieGenreEntity(

    @PrimaryKey(autoGenerate = false)
    val id: Int,

    @ColumnInfo(name = "name")
    val name: String

) : DtoModel()