package com.fahreziadha.githubprofile.data.remote

import com.fahreziadha.githubprofile.data.remote.dto.SearchUserResponseDTO
import com.fahreziadha.githubprofile.data.remote.dto.UserResponseDTO
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubProfileApi {

    @GET("/search/users")
    @Headers("Authorization:token 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")
    suspend fun getSearchUser(
        @Query("q") query: String,
        @Query("per_page") per_page: Int = 5,
        @Query("page") page: Int = 1
    ): SearchUserResponseDTO

    @GET("/users/{userName}")
    @Headers("Authorization:token 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")
    suspend fun getUser(@Path("userName") userName: String): UserResponseDTO


}