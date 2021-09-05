package com.selftaughtdev.data.cache.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.selftaughtdev.data.cache.room.entity.TVGenreEntity

@Dao
interface TvGenreDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTVGenres(list: List<TVGenreEntity>)

    @Query("select * from tv_genre")
    fun getTVGenres(): List<TVGenreEntity>

    @Query("delete from tv_genre")
    suspend fun deleteTVGenres()
}