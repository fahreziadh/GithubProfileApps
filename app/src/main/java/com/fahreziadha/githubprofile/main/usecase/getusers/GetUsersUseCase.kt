package com.fahreziadha.githubprofile.main.usecase.getusers

import com.fahreziadha.githubprofile.main.model.User

interface GetUsersUseCase {
    suspend operator fun invoke(query: String, page: Int): List<User>
}