package com.example.personalizednews.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NewsDao {
    @Insert
    suspend fun insertNews(news: List<NewsEntity>)

    @Query("SELECT * FROM news")
    suspend fun getAllNews(): List<NewsEntity>

    @Query("DELETE FROM news")
    suspend fun clearAll()
}
