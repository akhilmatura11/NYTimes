package com.newyork.times.di

import android.content.Context
import com.newyork.times.data.localDataSource.AppDatabase
import com.newyork.times.data.localDataSource.NewsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun getAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getDatabase(context)
    }

    @Singleton
    @Provides
    fun getNewsDao(appDatabase: AppDatabase): NewsDao {
        return appDatabase.newsDao()
    }
}