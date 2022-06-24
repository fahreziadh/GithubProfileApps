package com.fahreziadha.githubprofile.main.usecase.cache

import androidx.lifecycle.LiveData
import com.fahreziadha.githubprofile.main.model.CacheUser

interface DeleteCacheUserUseCase{
    suspend operator fun invoke()
}