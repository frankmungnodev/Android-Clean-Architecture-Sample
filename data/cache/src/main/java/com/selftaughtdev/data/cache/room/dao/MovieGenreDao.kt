package com.selftaughtdev.data.cache.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.selftaughtdev.data.cache.room.entity.MovieGenreEntity

@Dao
interface MovieGenreDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieGenres(list: List<MovieGenreEntity>)

    @Query("select * from movie_genre")
    fun getMovieGenres(): List<MovieGenreEntity>

    @Query("delete from movie_genre")
    suspend fun deleteMovieGenres()
}