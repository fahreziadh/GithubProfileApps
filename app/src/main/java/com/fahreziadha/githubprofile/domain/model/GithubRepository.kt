package com.fahreziadha.githubprofile.domain.model

data class GithubRepository(
    val rep_name: String,
    val owner_avatar: String,
    val rep_description: String,
    val rep_stargazers_count: Int,
    val rep_updated_at: String,
)
