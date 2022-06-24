package com.fahreziadha.githubprofile.domain.repository

import com.fahreziadha.githubprofile.data.remote.dto.SearchUserResponseDTO
import com.fahreziadha.githubprofile.data.remote.dto.SearchUserResponseItemDTO
import com.fahreziadha.githubprofile.domain.model.CacheUser
import com.fahreziadha.githubprofile.domain.model.User
import kotlinx.coroutines.flow.Flow


interface SearchRepository {

    fun getUsers(): Flow<List<CacheUser>>

    suspend fun getUserById(id: Int): CacheUser?

    suspend fun insertUser(user: CacheUser)

    suspend fun deleteAllUser()
}