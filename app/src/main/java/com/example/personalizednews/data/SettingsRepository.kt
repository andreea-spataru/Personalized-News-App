package com.example.personalizednews.data

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore("settings")

class SettingsRepository(private val context: Context) {

    // 🔹 Dark Mode
    private val DARK_MODE_KEY = booleanPreferencesKey("dark_mode")
    val isDarkMode: Flow<Boolean> = context.dataStore.data
        .map { preferences -> preferences[DARK_MODE_KEY] ?: false }

    suspend fun setDarkMode(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[DARK_MODE_KEY] = enabled
        }
    }

    // 🔹 Language Preference
    private val LANGUAGE_KEY = stringPreferencesKey("language")
    val selectedLanguage: Flow<String> = context.dataStore.data
        .map { preferences -> preferences[LANGUAGE_KEY] ?: "en" }

    suspend fun setLanguage(lang: String) {
        context.dataStore.edit { preferences ->
            preferences[LANGUAGE_KEY] = lang
        }
    }
}
