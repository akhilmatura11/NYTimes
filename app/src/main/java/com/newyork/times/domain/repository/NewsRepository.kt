package com.newyork.times.domain.repository

import androidx.lifecycle.LiveData
import com.newyork.times.domain.models.ApiResponse
import com.newyork.times.domain.models.NewsEntity
import retrofit2.Response

interface NewsRepository {

    suspend fun fetchMostPopularNews(): Response<ApiResponse>

    fun getNews(): LiveData<List<NewsEntity>>
}