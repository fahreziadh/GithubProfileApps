package com.fahreziadha.githubprofile.domain.model

data class User(
    val id: Int,
    val name: String,
    val login: String,
    val location: String,
    val email: String,
    val bio: String,
    val avatarUrl: String,
    val company: String
)