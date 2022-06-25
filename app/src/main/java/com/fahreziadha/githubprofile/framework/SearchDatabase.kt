package com.fahreziadha.githubprofile.framework

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fahreziadha.githubprofile.main.framework.db.SearchDao
import com.fahreziadha.githubprofile.main.model.CacheUser

@Database(
    entities = [CacheUser::class],
    exportSchema = false,
    version = 4
)

abstract class SearchDatabase : RoomDatabase() {

    abstract fun searchDao(): SearchDao

    companion object {
        const val DATABASE_NAME = "search_db"
    }

}