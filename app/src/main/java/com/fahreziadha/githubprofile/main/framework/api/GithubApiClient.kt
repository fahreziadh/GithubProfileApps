package com.fahreziadha.githubprofile.main.framework.api

import com.fahreziadha.githubprofile.main.model.GetUserResponseDTO
import com.fahreziadha.githubprofile.main.model.GithubReposResponseDTO
import com.fahreziadha.githubprofile.main.model.UserResponseDTO
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApiClient {

    @GET("/search/users")
    @Headers("Authorization:token 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")
    suspend fun getSearchUser(
        @Query("q") query: String,
        @Query("per_page") per_page: Int = 5,
        @Query("page") page: Int = 1
    ): GetUserResponseDTO

    @GET("/users/{login}")
    @Headers("Authorization:token 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")
    suspend fun getUser(@Path("login") login: String): UserResponseDTO


    @GET("/users/{login}/repos")
    @Headers("Authorization:token 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")
    suspend fun getUserRepos(@Path("login") login: String): List<GithubReposResponseDTO>


}