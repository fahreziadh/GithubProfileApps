package com.fahreziadha.githubprofile.main.framework.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.fahreziadha.githubprofile.main.model.CacheUser

@Dao
interface SearchDao {
    @Query("SELECT * FROM user ORDER BY timeStamp DESC")
    fun getUsers(): LiveData<List<CacheUser>>

    @Query("SELECT * FROM user WHERE login = :id")
    suspend fun getUserById(id: Int): CacheUser?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: CacheUser)


    @Query("DELETE FROM user")
    suspend fun deleteAllUser()
}