package com.fahreziadha.githubprofile.domain.use_case.local

import com.fahreziadha.githubprofile.domain.repository.SearchRepository

class DeleteSearch(val repository: SearchRepository) {
    suspend operator fun invoke() = repository.deleteAllUser()
}