package com.fahreziadha.githubprofile.main.framework.datasource

import com.fahreziadha.githubprofile.main.model.CacheUser

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


val successfulResponse = """
    {
      "incompleteResult": false,
      "items": [],
      "totalCount": 100
    }
""".trimIndent()

val errorResponse = "I am not a json :o"