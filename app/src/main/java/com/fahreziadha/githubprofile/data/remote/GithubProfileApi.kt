package com.fahreziadha.githubprofile.data.remote

import com.fahreziadha.githubprofile.data.remote.dto.SearchUserResponseDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubProfileApi {

    @GET("/search/users")
    suspend fun getSearchUser(@Query("q") query: String = "fahrezi"): SearchUserResponseDTO
}