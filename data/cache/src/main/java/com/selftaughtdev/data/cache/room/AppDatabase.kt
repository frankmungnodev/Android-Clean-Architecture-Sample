package com.selftaughtdev.data.cache.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.selftaughtdev.data.cache.room.dao.MovieDao
import com.selftaughtdev.data.cache.room.dao.MovieGenreDao
import com.selftaughtdev.data.cache.room.dao.TvGenreDao
import com.selftaughtdev.data.cache.room.entity.MovieEntity
import com.selftaughtdev.data.cache.room.entity.MovieGenreEntity
import com.selftaughtdev.data.cache.room.entity.TVGenreEntity
import com.selftaughtdev.data.cache.room.helper.ListConverter

@Database(
    entities = [MovieEntity::class, MovieGenreEntity::class, TVGenreEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(ListConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract val movieDao: MovieDao
    abstract val movieGenreDao: MovieGenreDao
    abstract val tvGenreDao: TvGenreDao
}