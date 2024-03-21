package com.newyork.times.di

import com.newyork.times.data.NewsRepositoryImpl
import com.newyork.times.data.localDataSource.NewsDao
import com.newyork.times.data.remoteDataSource.ApiInterface
import com.newyork.times.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun getRepository(apiInterface: ApiInterface, newsDao: NewsDao): NewsRepository {
        return NewsRepositoryImpl(apiInterface, newsDao)
    }
}