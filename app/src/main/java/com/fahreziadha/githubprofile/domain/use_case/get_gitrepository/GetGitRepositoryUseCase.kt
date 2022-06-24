package com.fahreziadha.githubprofile.domain.use_case.get_gitrepository

import com.fahreziadha.githubprofile.common.Resource
import com.fahreziadha.githubprofile.data.remote.dto.GithubRepositoryResponseDTO
import com.fahreziadha.githubprofile.data.remote.dto.UserResponseDTO
import com.fahreziadha.githubprofile.data.remote.dto.toGithubRepository
import com.fahreziadha.githubprofile.domain.model.GithubRepository
import com.fahreziadha.githubprofile.domain.repository.GithubRepRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetGitRepositoryUseCase @Inject constructor(
    val repository: GithubRepRepository
) {
    operator fun invoke(userName: String): Flow<Resource<List<GithubRepository>>> = flow {
        try {
            emit(Resource.Loading<List<GithubRepository>>())
            val res = repository.getRepository(userName).map { it.toGithubRepository() }
            emit(Resource.Success<List<GithubRepository>>(res))
        } catch (e: HttpException) {
            emit(
                Resource.Error<List<GithubRepository>>(
                    e.localizedMessage ?: "An unexpected error occured"
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error<List<GithubRepository>>("Couldn't reach server. Check your internet connection."))
        }
    }
}