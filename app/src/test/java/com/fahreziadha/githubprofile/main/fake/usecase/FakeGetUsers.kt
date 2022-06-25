package com.fahreziadha.githubprofile.main.fake.usecase

import com.fahreziadha.githubprofile.main.model.User
import com.fahreziadha.githubprofile.main.usecase.getusers.GetUsersUseCase
import com.fahreziadha.githubprofile.main.user1
import com.fahreziadha.githubprofile.main.user2


class FakeGetUsers(private val isSuccessful: Boolean = true) : GetUsersUseCase {

    var listUser: List<User>? = null

    override suspend fun invoke(query: String, page: Int): List<User> {
        return if (isSuccessful) listUser ?: listOf(user1, user2) else emptyList()
    }
}