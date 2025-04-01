package com.example.personalizednews.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.personalizednews.data.*
import com.example.stiripersonalizate.data.News
import com.example.stiripersonalizate.network.NewsApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlinx.coroutines.flow.*

class NewsViewModel(application: Application) : AndroidViewModel(application) {

    private val settingsRepository = SettingsRepository(application)

    private val retrofitService = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:5000/") // pentru emulator
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NewsApi::class.java)

    private val _newsList = MutableStateFlow<List<News>>(emptyList())
    val newsList: StateFlow<List<News>> = _newsList

    private val _category = MutableStateFlow("technology")
    val category: StateFlow<String> = _category

    init {
        fetchNews(_category.value)
    }

    fun setCategory(newCategory: String) {
        _category.value = newCategory
        fetchNews(newCategory)
    }

    private fun fetchNews(category: String) {
        viewModelScope.launch {
            try {
                val lang = settingsRepository.selectedLanguage.first() // preluăm limba salvată
                println("📡 Cerere API: category=$category, lang=$lang")

                val news = retrofitService.getNews(category, lang)
                _newsList.value = news
            } catch (e: Exception) {
                println("❌ Eroare la fetchNews: ${e.message}")
                e.printStackTrace()
            }
        }
    }
}
