package com.fahreziadha.githubprofile.domain.use_case.local

data class LocalUseCase(
    val getListSearch: GetListSearch,
    val deleteSearch: DeleteSearch,
    val addSearch: AddSearch,
    val getSearch: GetSearch
)

class InvalidLocalException(message: String) : Exception(message)