package com.fahreziadha.githubprofile.data.local.repository

import com.fahreziadha.githubprofile.data.local.data_source.SearchDao
import com.fahreziadha.githubprofile.data.remote.dto.SearchUserResponseDTO
import com.fahreziadha.githubprofile.data.remote.dto.SearchUserResponseItemDTO
import com.fahreziadha.githubprofile.domain.model.CacheUser
import com.fahreziadha.githubprofile.domain.model.User
import com.fahreziadha.githubprofile.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow

class SearchRepositoryImpl(
    private val dao: SearchDao
) : SearchRepository {
    override fun getUsers(): Flow<List<CacheUser>> {
        return dao.getUsers()
    }

    override suspend fun getUserById(id: Int): CacheUser? {
        return dao.getUserById(id)
    }

    override suspend fun insertUser(user: CacheUser) {
        dao.insertUser(user)
    }

    override suspend fun deleteAllUser() {
        dao.deleteAllUser()
    }

}