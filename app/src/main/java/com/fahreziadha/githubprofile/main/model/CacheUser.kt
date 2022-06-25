package com.fahreziadha.githubprofile.main.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user")
data class CacheUser(
    @PrimaryKey
    val id: Int?,
    var name: String?,
    var login: String?,
    var location: String?,
    val email: String?,
    val bio: String?,
    val avatarUrl: String?,
    val company: String?,
    val timeStamp: Long?
)