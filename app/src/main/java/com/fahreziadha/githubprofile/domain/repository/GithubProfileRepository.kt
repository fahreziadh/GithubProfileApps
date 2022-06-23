package com.fahreziadha.githubprofile.domain.repository

import com.fahreziadha.githubprofile.data.remote.dto.SearchUserResponseDTO
import com.fahreziadha.githubprofile.data.remote.dto.UserResponseDTO

interface GithubProfileRepository {
    suspend fun getSearchUsers(query: String,per_page:Int,page:Int):SearchUserResponseDTO
    suspend fun getUser(username:String): UserResponseDTO
}