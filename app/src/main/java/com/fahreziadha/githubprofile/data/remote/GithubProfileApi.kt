package com.fahreziadha.githubprofile.data.remote

import retrofit2.http.GET

interface GithubProfileApi {
    @GET("/search/users")
    suspend fun getSearchUser(): List<String>
}