package com.fahreziadha.githubprofile.data.local.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fahreziadha.githubprofile.domain.model.CacheUser
import com.fahreziadha.githubprofile.domain.model.User

@Database(
    entities = [CacheUser::class],
    version = 1
)

abstract class SearchDatabase : RoomDatabase() {

    abstract val searchDao: SearchDao

    companion object{
        const val DATABASE_NAME="search_db"
    }

}