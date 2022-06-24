package com.fahreziadha.githubprofile.data.local.data_source

import androidx.room.*
import com.fahreziadha.githubprofile.data.remote.dto.SearchUserResponseDTO
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchDao {
    @Query("SELECT * FROM user")
    fun getUsers(): Flow<List<SearchUserResponseDTO>>

    @Query("SELECT * FROM user WHERE login = :id")
    suspend fun getUserById(id: Int): SearchUserResponseDTO?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: SearchUserResponseDTO)

    @Delete
    suspend fun deleteUser(user: SearchUserResponseDTO)
}