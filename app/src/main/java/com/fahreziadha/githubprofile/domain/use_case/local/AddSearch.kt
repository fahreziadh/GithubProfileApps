package com.fahreziadha.githubprofile.domain.use_case.local

import com.fahreziadha.githubprofile.data.remote.dto.SearchUserResponseDTO
import com.fahreziadha.githubprofile.domain.repository.SearchRepository

class AddSearch(private val repository: SearchRepository) {
    @Throws(InvalidLocalException::class)
    suspend operator fun invoke(user: SearchUserResponseDTO) {
        repository.insertUser(user)
    }
}

