package com.fahreziadha.githubprofile.domain.use_case.get_user

import com.fahreziadha.githubprofile.common.Resource
import com.fahreziadha.githubprofile.data.remote.dto.SearchUserResponseDTO
import com.fahreziadha.githubprofile.data.remote.dto.UserResponseDTO
import com.fahreziadha.githubprofile.domain.repository.GithubProfileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val repository: GithubProfileRepository
) {

    operator fun invoke(userName:String): Flow<Resource<UserResponseDTO>> = flow {
        try {
            emit(Resource.Loading<UserResponseDTO>())
            val res = repository.getUser(userName)
            emit(Resource.Success<UserResponseDTO>(res))
        } catch (e: HttpException) {
            emit(Resource.Error<UserResponseDTO>(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error<UserResponseDTO>("Couldn't reach server. Check your internet connection."))
        }
    }

}