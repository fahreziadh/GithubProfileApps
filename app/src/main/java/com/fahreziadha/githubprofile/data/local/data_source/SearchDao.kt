package com.fahreziadha.githubprofile.data.local.data_source

import androidx.room.*
import com.fahreziadha.githubprofile.domain.model.CacheUser
import com.fahreziadha.githubprofile.domain.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchDao {
    @Query("SELECT * FROM user ORDER BY timeStamp DESC")
    fun getUsers(): Flow<List<CacheUser>>

    @Query("SELECT * FROM user WHERE login = :id")
    suspend fun getUserById(id: Int): CacheUser?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: CacheUser)


    @Query("DELETE FROM user")
    suspend fun deleteAllUser()
}