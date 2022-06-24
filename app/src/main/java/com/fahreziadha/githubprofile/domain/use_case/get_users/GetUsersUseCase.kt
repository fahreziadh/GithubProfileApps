package com.fahreziadha.githubprofile.domain.use_case.get_users

import com.fahreziadha.githubprofile.common.Resource
import com.fahreziadha.githubprofile.data.remote.dto.SearchUserResponseDTO
import com.fahreziadha.githubprofile.data.remote.dto.toUser
import com.fahreziadha.githubprofile.domain.model.User
import com.fahreziadha.githubprofile.domain.repository.GithubProfileRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val repository: GithubProfileRepository
) {

    operator fun invoke(query: String, page: Int): Flow<Resource<List<User>>> = flow {
        try {
            emit(Resource.Loading<List<User>>())
            val searchUserRes = repository.getSearchUsers(query, per_page = 10, page)
            val list = mutableListOf<User>()

            searchUserRes.items.map { searchUser ->
                val detailUserRes = repository.getUser(searchUser.login)
                list.add(detailUserRes.toUser())
            }

            emit(Resource.Success<List<User>>(list))
        } catch (e: HttpException) {
            emit(Resource.Error<List<User>>(e.localizedMessage ?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(Resource.Error<List<User>>("Couldn't reach server. Check your internet connection."))
        }
    }

    fun test() = flow {
        for (i in 0..100) {
            delay(300)
            emit(i)
        }
    }

}