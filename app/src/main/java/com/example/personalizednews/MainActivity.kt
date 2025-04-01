package com.example.personalizednews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.personalizednews.ui.MainScreen
import com.example.personalizednews.ui.ui.PersonalizedNewsTheme
import com.example.personalizednews.ui.AppNavigation
import com.example.personalizednews.ui.SettingsScreen
import androidx.compose.runtime.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var isDarkMode by remember { mutableStateOf(false) }

            PersonalizedNewsTheme(darkTheme = isDarkMode) {
                AppNavigation(onThemeChanged = { isDarkMode = it })
            }
        }
    }
}
