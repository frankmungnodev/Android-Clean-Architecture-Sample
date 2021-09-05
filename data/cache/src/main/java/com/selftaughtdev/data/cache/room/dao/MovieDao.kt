package com.selftaughtdev.data.cache.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.selftaughtdev.data.cache.room.entity.MovieEntity

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDiscoverMovies(movie: MovieEntity)

    @Query("select * from movie_table where source =:source limit :limit offset :skip ")
    fun getDiscoverMovies(limit: Int, skip: Int, source: String): List<MovieEntity>

    @Query("delete from movie_table where source =:source")
    suspend fun deleteDiscoverMovies(source: String)
}