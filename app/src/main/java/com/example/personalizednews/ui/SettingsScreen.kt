package com.example.personalizednews.ui

import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.personalizednews.viewmodel.SettingsViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun SettingsScreen(
    onThemeChanged: (Boolean) -> Unit
) {
    val context = LocalContext.current.applicationContext as Application

    val viewModel: SettingsViewModel = viewModel(
        factory = viewModelFactory {
            initializer {
                SettingsViewModel(context)
            }
        }
    )

    val scope = rememberCoroutineScope()
    var darkMode by remember { mutableStateOf(false) }
    var selectedLang by remember { mutableStateOf("en") }

    // Colectăm valoarea dark mode
    LaunchedEffect(Unit) {
        viewModel.isDarkMode.collectLatest {
            darkMode = it
            onThemeChanged(it)
        }
    }

    // Colectăm limba salvată
    LaunchedEffect(Unit) {
        viewModel.selectedLanguage.collectLatest {
            selectedLang = it
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 🔘 Dark Mode Toggle
        Text("Mod Întunecat", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(12.dp))
        Switch(
            checked = darkMode,
            onCheckedChange = {
                darkMode = it
                viewModel.setDarkMode(it)
                onThemeChanged(it)
            }
        )

        Spacer(Modifier.height(48.dp))

        // 🌐 Language Dropdown
        Text("Limbă", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(12.dp))

        DropdownMenuBox(
            options = listOf("ro", "en"),
            selected = selectedLang,
            onSelect = {
                selectedLang = it
                viewModel.setLanguage(it)
            }
        )
    }
}

@Composable
fun DropdownMenuBox(
    options: List<String>,
    selected: String,
    onSelect: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        Button(onClick = { expanded = true }) {
            Text(text = selected.uppercase())
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option.uppercase()) },
                    onClick = {
                        onSelect(option)
                        expanded = false
                    }
                )
            }
        }
    }
}
