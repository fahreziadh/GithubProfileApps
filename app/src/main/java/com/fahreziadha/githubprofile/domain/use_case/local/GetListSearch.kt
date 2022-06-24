package com.fahreziadha.githubprofile.domain.use_case.local

import com.fahreziadha.githubprofile.data.remote.dto.SearchUserResponseDTO
import com.fahreziadha.githubprofile.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetListSearch(private val repository: SearchRepository) {
    @Throws(InvalidLocalException::class)
    operator fun invoke(): Flow<List<SearchUserResponseDTO>> {
        return repository.getUsers()
    }
}