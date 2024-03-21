package com.newyork.times.data.remoteDataSource

import com.newyork.times.domain.models.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("mostpopular/v2/mostviewed/all-sections/7.json")
    suspend fun fetchMostPopularNews(@Query("api-key") apiKey: String): Response<ApiResponse>
}