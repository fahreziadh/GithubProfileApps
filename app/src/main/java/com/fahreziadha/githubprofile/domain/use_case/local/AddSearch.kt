package com.fahreziadha.githubprofile.domain.use_case.local

import com.fahreziadha.githubprofile.data.remote.dto.SearchUserResponseDTO
import com.fahreziadha.githubprofile.data.remote.dto.SearchUserResponseItemDTO
import com.fahreziadha.githubprofile.domain.model.CacheUser
import com.fahreziadha.githubprofile.domain.model.User
import com.fahreziadha.githubprofile.domain.repository.SearchRepository

class AddSearch(private val repository: SearchRepository) {
    @Throws(InvalidLocalException::class)
    suspend operator fun invoke(user: CacheUser) {
        repository.insertUser(user)
    }
}

