package com.fahreziadha.githubprofile.domain.use_case.get_users

import com.fahreziadha.githubprofile.common.Resource
import com.fahreziadha.githubprofile.domain.repository.GithubProfileRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val repository: GithubProfileRepository
) {

}