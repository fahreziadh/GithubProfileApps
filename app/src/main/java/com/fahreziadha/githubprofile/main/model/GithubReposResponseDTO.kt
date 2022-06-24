package com.fahreziadha.githubprofile.main.model

import com.squareup.moshi.Json


data class GithubReposResponseDTO(
    @Json(name = "name")
    val name: String,
    @Json(name = "owner")
    val owner: Owner,
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

data class Owner(
    @Json(name = "avatar_url")
    val avatar_url: String,
    @Json(name = "events_url")
    val eventsUrl: String,
    @Json(name = "followers_url")
    val followersUrl: String,
    @Json(name = "following_url")
    val followingUrl: String,
    @Json(name = "gists_url")
    val gistsUrl: String,
    @Json(name = "gravatar_id")
    val gravatarId: String,
    @Json(name = "html_url")
    val htmlUrl: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "login")
    val login: String,
    @Json(name = "node_id")
    val nodeId: String,
    @Json(name = "organizations_url")
    val organizationsUrl: String,
    @Json(name = "received_events_url")
    val receivedEventsUrl: String,
    @Json(name = "repos_url")
    val reposUrl: String,
    @Json(name = "site_admin")
    val siteAdmin: Boolean,
    @Json(name = "starred_url")
    val starredUrl: String,
    @Json(name = "subscriptions_url")
    val subscriptionsUrl: String,
    @Json(name = "type")
    val type: String,
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
