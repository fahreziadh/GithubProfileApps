package com.fahreziadha.githubprofile.main

import com.fahreziadha.githubprofile.main.model.*

val user1 = User(
    id = 1,
    name = "user 1",
    login = "user1",
    location = "loc1",
    bio = "bio1",
    email = "email1",
    avatarUrl = "avatar1",
    company = "company1"
)
val user2 = User(
    id = 2,
    name = "user 2",
    login = "user2",
    location = "loc2",
    bio = "bio2",
    email = "email2",
    avatarUrl = "avatar2",
    company = "company2"
)
val user3 = User(
    id = 3,
    name = "user 3",
    login = "user3",
    location = "loc3",
    bio = "bio3",
    email = "email3",
    avatarUrl = "avatar3",
    company = "company3"
)

val cacheUser1 = CacheUser(
    id = 1,
    name = "user 1",
    login = "user1",
    location = "loc1",
    bio = "bio1",
    email = "email1",
    avatarUrl = "avatar1",
    company = "company1",
    timeStamp = 0
)
val cacheUser2 = CacheUser(
    id = 2,
    name = "user 2",
    login = "user2",
    location = "loc2",
    bio = "bio2",
    email = "email2",
    avatarUrl = "avatar2",
    company = "company2",
    timeStamp = 0
)


val getUserResponseDTO1 = GetUserResponseDTO(
    false, emptyList(), 100
)


val userResponseDTO1 = UserResponseDTO(
    "1",
    "1",
    "1",
    "1",
    "1",
    "1",
    "1",
    1,
    "1",
    1,
    "1",
    "1",
    "1",
    "1",
    "1",
    1,
    "1",
    "1",
    "1",
    "1",
    "1",
    1,
    1,
    "1",
    "1",
    false,
    "1",
    "1",
    "1",
    "1",
    "1",
    "1"
)


val userResponseDTO2 = UserResponseDTO(
    "2",
    "2",
    "2",
    "2",
    "2",
    "2",
    "2",
    2,
    "2",
    2,
    "2",
    "2",
    "2",
    "2",
    "2",
    2,
    "2",
    "2",
    "2",
    "2",
    "2",
    2,
    2,
    "2",
    "2",
    false,
    "2",
    "2",
    "2",
    "2",
    "2",
    "2"
)

val githubReposResponseDTO1= GithubReposResponseDTO("", owner = userResponseDTO1,"","","")
