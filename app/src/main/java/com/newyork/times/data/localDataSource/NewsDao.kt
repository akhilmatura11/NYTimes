package com.newyork.times.data.localDataSource

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.newyork.times.domain.models.NewsEntity

@Dao
interface NewsDao {

    @Query("Select * from NewsEntity")
    fun getNewsList(): LiveData<List<NewsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNews(newsList: List<NewsEntity>)

    @Query("Delete from NewsEntity")
    fun deleteAll()
}