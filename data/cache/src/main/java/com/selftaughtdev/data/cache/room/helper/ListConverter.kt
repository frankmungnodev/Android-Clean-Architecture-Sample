package com.selftaughtdev.data.cache.room.helper

import androidx.room.TypeConverter

class ListConverter {

    @TypeConverter
    fun restoreList(listOfString: String?): List<Int?>? {
        return listOfString?.split(",")?.map { it.toInt() }
    }

    @TypeConverter
    fun saveList(listOfString: List<Int?>?): String? {
        return listOfString?.joinToString(",")
    }

}