package com.example.stiripersonalizate.network

import com.example.stiripersonalizate.data.News
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("/news")
    suspend fun getNews(
        @Query("category") category: String,
        @Query("lang") lang: String
    ): List<News>
}
