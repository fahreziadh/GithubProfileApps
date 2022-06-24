package com.fahreziadha.githubprofile.domain.repository

import com.fahreziadha.githubprofile.data.remote.dto.SearchUserResponseDTO
import kotlinx.coroutines.flow.Flow


interface SearchRepository {

    fun getUsers(): Flow<List<SearchUserResponseDTO>>

    suspend fun getUserById(id: Int): SearchUserResponseDTO?

    suspend fun insertUser(note: SearchUserResponseDTO)

    suspend fun deleteUser(note: SearchUserResponseDTO)
}