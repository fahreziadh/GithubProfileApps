package com.fahreziadha.githubprofile.main.usecase.getusers

import com.fahreziadha.githubprofile.main.model.GetUserResponseDTO
import com.fahreziadha.githubprofile.main.model.User
import com.fahreziadha.githubprofile.main.repository.UserRepository
import com.fahreziadha.githubprofile.model.Result
import javax.inject.Inject

class GetUsersUseCaseImpl @Inject constructor(
    private val userRepository: UserRepository
) : GetUsersUseCase {

    override suspend fun invoke(query: String, page: Int): List<User> {
        return userRepository.getSearchUsers(query = query,10,page=page)
    }

}