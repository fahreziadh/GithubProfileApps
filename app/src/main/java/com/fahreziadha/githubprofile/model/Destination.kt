package com.fahreziadha.githubprofile.model

sealed class Destination(
    val path: String
) {

    object Search : Destination("search")
    object Detail : Destination("detail")

}