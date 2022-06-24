package com.fahreziadha.githubprofile.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

open class User(
    val id: Int,
    var name: String,
    var login: String,
    var location: String,
    val email: String,
    val bio: String,
    val avatarUrl: String,
    val company: String
)


@Entity(tableName = "user")
data class CacheUser(
    @PrimaryKey
    val id: Int,
    var name: String,
    var login: String,
    var location: String,
    val email: String,
    val bio: String,
    val avatarUrl: String,
    val company: String,
    val timeStamp: Long
)

