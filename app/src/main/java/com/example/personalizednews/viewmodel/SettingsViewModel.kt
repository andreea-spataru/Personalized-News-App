package com.example.personalizednews.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.personalizednews.data.SettingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class SettingsViewModel(application: Application) : AndroidViewModel(application) {

    private val repo = SettingsRepository(application)
    val isDarkMode: Flow<Boolean> = repo.isDarkMode

    fun setDarkMode(enabled: Boolean) {
        viewModelScope.launch {
            repo.setDarkMode(enabled)
        }
    }

    val selectedLanguage: Flow<String> = repo.selectedLanguage

    fun setLanguage(lang: String) {
        viewModelScope.launch {
            repo.setLanguage(lang)
        }
    }
}

