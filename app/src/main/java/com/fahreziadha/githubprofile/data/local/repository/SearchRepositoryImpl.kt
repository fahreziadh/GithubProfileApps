package com.fahreziadha.githubprofile.data.local.repository

import com.fahreziadha.githubprofile.data.local.data_source.SearchDao
import com.fahreziadha.githubprofile.data.remote.dto.SearchUserResponseDTO
import com.fahreziadha.githubprofile.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow

class SearchRepositoryImpl(
    private val dao: SearchDao
) : SearchRepository {
    override fun getUsers(): Flow<List<SearchUserResponseDTO>> {
        return dao.getUsers()
    }

    override suspend fun getUserById(id: Int): SearchUserResponseDTO? {
        return dao.getUserById(id)
    }

    override suspend fun insertUser(user: SearchUserResponseDTO) {
        dao.insertUser(user)
    }

    override suspend fun deleteUser(user: SearchUserResponseDTO) {
        dao.deleteUser(user)
    }
}