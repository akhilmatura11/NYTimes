package com.newyork.times.data

import com.newyork.times.data.localDataSource.NewsDao
import com.newyork.times.data.remoteDataSource.ApiInterface
import com.newyork.times.domain.models.ApiResponse
import com.newyork.times.domain.models.toNewsList
import com.newyork.times.domain.repository.NewsRepository
import com.newyork.times.utils.AppConstants.Companion.API_KEY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val apiInterface: ApiInterface,
    private val newsDao: NewsDao
) : NewsRepository {
    override suspend fun fetchMostPopularNews(): Response<ApiResponse> {
        val response = apiInterface.fetchMostPopularNews(API_KEY)
        if (response.isSuccessful) {
            response.body()?.let {
                withContext(Dispatchers.IO) {
                    val newsList = it.toNewsList()
                    newsDao.deleteAll()
                    newsDao.insertNews(newsList)
                }
            }
        }
        return response
    }

    override fun getNews() = newsDao.getNewsList()
}