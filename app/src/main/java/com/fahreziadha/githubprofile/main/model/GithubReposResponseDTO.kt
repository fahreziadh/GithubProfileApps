package com.fahreziadha.githubprofile.main.model

import com.squareup.moshi.Json


data class GithubReposResponseDTO(
    @Json(name = "name")
    val name: String,
    @Json(name = "owner")
    val owner: UserResponseDTO,
    @Json(name = "description")
    val description: String,
    @Json(name = "stargazers_count")
    val stargazers_count: String,
    @Json(name = "updated_at")
    val updated_at: String,
)

data class License(
    @Json(name = "key")
    val key: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "node_id")
    val nodeId: String,
    @Json(name = "spdx_id")
    val spdxId: String,
    @Json(name = "url")
    val url: String
)


data class GithubRepository(
    val rep_name: String? = "",
    val owner_avatar: String? = "",
    val rep_description: String? = "",
    val rep_stargazers_count: Int? = 0,
    val rep_updated_at: String? = "",
)
