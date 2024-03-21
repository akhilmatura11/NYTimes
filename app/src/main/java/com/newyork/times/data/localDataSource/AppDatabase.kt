package com.newyork.times.data.localDataSource

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.newyork.times.domain.models.NewsEntity

@Database(entities = [NewsEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao

    companion object {
        private lateinit var appDatabase: AppDatabase

        fun getDatabase(context: Context): AppDatabase {
            if (!Companion::appDatabase.isInitialized) {
                synchronized(AppDatabase::class.java) {
                    if (!Companion::appDatabase.isInitialized) {
                        appDatabase = Room.databaseBuilder(
                            context.applicationContext,
                            AppDatabase::class.java,
                            "NYTimesMostPopular"
                        ).build()
                    }
                }
            }
            return appDatabase
        }
    }
}