package com.newyork.times.presentation.newsList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.newyork.times.domain.models.NewsEntity
import com.newyork.times.domain.repository.NewsRepository
import com.newyork.times.utils.ResponseStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {

    val newsList: LiveData<List<NewsEntity>> = repository.getNews()
    val responseStatus = MutableLiveData<ResponseStatus>()
    private var isInitialized = false

    fun fetchNews() {
        if (!isInitialized) {
            viewModelScope.launch {
                responseStatus.postValue(ResponseStatus.Loading())
                try {
                    val response = repository.fetchMostPopularNews()
                    if (response.isSuccessful) {
                        isInitialized = true
                        responseStatus.postValue(ResponseStatus.Success())
                    } else {
                        responseStatus.postValue(
                            ResponseStatus.Error(response.errorBody().toString())
                        )
                    }
                } catch (t: Throwable) {
                    Log.e("MostPopularViewModel", "Fetching Api Response", t)
                    when (t) {
                        is IOException -> responseStatus.postValue(
                            ResponseStatus.Error("Network Failure")
                        )
                        else -> responseStatus.postValue(
                            ResponseStatus.Error("Conversion Error")
                        )
                    }
                }
            }
        }
    }

    fun onRetryButtonClicked() {
        fetchNews()
    }
}