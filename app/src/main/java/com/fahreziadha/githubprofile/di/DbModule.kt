package com.fahreziadha.githubprofile.di

import android.app.Application
import androidx.room.Room
import com.fahreziadha.githubprofile.framework.SearchDatabase
import com.fahreziadha.githubprofile.main.framework.db.SearchDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DbModule {
    @Provides
    @Singleton
    fun provideRoom(app: Application): SearchDatabase {
        return Room.databaseBuilder(
            app,
            SearchDatabase::class.java,
            SearchDatabase.DATABASE_NAME
        ).build()
    }


    @Provides
    @Singleton
    fun provideUserDao(db: SearchDatabase): SearchDao {
        return db.searchDao()
    }
}