package com.fahreziadha.githubprofile.domain.use_case.get_users

import com.fahreziadha.githubprofile.common.Resource
import com.fahreziadha.githubprofile.data.remote.dto.SearchUserResponseDTO
import com.fahreziadha.githubprofile.domain.repository.GithubProfileRepository
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val repository: GithubProfileRepository
) {

    operator fun invoke(query:String): Flow<Resource<SearchUserResponseDTO>> = flow {
        try {
            emit(Resource.Loading<SearchUserResponseDTO>())
            val res = repository.getSearchUsers(query)
            emit(Resource.Success<SearchUserResponseDTO>(res))
        } catch (e: HttpException) {
            emit(Resource.Error<SearchUserResponseDTO>(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error<SearchUserResponseDTO>("Couldn't reach server. Check your internet connection."))
        }
    }

}