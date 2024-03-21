package com.newyork.times

import androidx.lifecycle.MutableLiveData
import com.newyork.times.domain.models.NewsEntity
import com.newyork.times.domain.repository.NewsRepository
import com.newyork.times.presentation.newsList.NewsListViewModel
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class NewsViewModelTest {

    @Test
    fun test_newsList_empty() {
        val expected = 0
        val repository: NewsRepository = mock()
        whenever(repository.getNews())
            .thenReturn(MutableLiveData(arrayListOf()))
        val model = NewsListViewModel(repository)

        val newsList = model.newsList.value
        assertEquals(expected, newsList!!.size)
    }

    @Test
    fun test_newsList_single() {
        val expected = 1
        val repository: NewsRepository = mock()
        whenever(repository.getNews())
            .thenReturn(
                MutableLiveData(
                    arrayListOf(
                        NewsEntity(
                            "Testing news list view model",
                            "Test1",
                            "Test1",
                            "2022-01-02",
                            "Testing",
                            "",
                            "",
                            "",
                            "1"
                        )
                    )
                )
            )
        val model = NewsListViewModel(repository)

        val newsList = model.newsList.value
        assertEquals(expected, newsList!!.size)
    }

    @Test
    fun test_newsList_multiple() {
        val expected = 3
        val repository: NewsRepository = mock()
        whenever(repository.getNews())
            .thenReturn(
                MutableLiveData(
                    arrayListOf(
                        NewsEntity(
                            "Testing news list view model",
                            "Test1",
                            "Test1",
                            "2022-01-02",
                            "Testing",
                            "",
                            "",
                            "",
                            "1"
                        ),
                        NewsEntity(
                            "Testing news list view model",
                            "Test2",
                            "Test2",
                            "2022-01-03",
                            "Testing",
                            "",
                            "",
                            "",
                            "2"
                        ),
                        NewsEntity(
                            "Testing news list view model",
                            "Test3",
                            "Test3",
                            "2022-01-04",
                            "Testing",
                            "",
                            "",
                            "",
                            "3"
                        )
                    )
                )
            )
        val model = NewsListViewModel(repository)

        val newsList = model.newsList.value
        assertEquals(expected, newsList!!.size)
    }

}