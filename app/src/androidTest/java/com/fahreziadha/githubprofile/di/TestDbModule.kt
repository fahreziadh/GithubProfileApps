package com.fahreziadha.githubprofile.di

import android.content.Context
import androidx.room.Room
import com.fahreziadha.githubprofile.framework.SearchDatabase
import com.fahreziadha.githubprofile.main.framework.db.SearchDao
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton


@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DbModule::class]
)
object TestDbModule {

    @Provides
    @Singleton
    fun provideRoom(@ApplicationContext context: Context): SearchDatabase {
        return Room.inMemoryDatabaseBuilder(
            context,
            SearchDatabase::class.java
        ).build()
    }

    @Provides
    @Singleton
    fun provideActivityDao(appDatabase: SearchDatabase): SearchDao {
        return appDatabase.searchDao()
    }
}