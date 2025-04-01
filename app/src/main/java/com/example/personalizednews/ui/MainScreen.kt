package com.example.personalizednews.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.personalizednews.viewmodel.NewsViewModel
import com.example.stiripersonalizate.data.News
import androidx.navigation.NavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import com.example.personalizednews.R
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavController,
    viewModel: NewsViewModel = viewModel()
) {
    val newsList by viewModel.newsList.collectAsState()
    val category by viewModel.category.collectAsState()
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Știri Personalizate") },
                actions = {
                    IconButton(onClick = {
                        navController.navigate("settings")
                    }) {
                        Icon(Icons.Default.Settings, contentDescription = "Setări")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(padding)
                .padding(16.dp)
        ) {
            val imageRes = when (category) {
                "sports" -> R.drawable.sports
                "technology" -> R.drawable.technology
                "business" -> R.drawable.business
                "health" -> R.drawable.health
                else -> R.drawable.technology
            }

            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))

            CategoryDropdown(
                selectedCategory = category,
                onCategorySelected = { viewModel.setCategory(it) }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text("Număr știri: ${newsList.size}")
            Spacer(modifier = Modifier.height(8.dp))

            newsList.forEach { article ->
                NewsItem(news = article, navController = navController)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}


@Composable
fun CategoryDropdown(selectedCategory: String, onCategorySelected: (String) -> Unit) {
    val categories = listOf("technology", "sports", "business", "health")
    var expanded by remember { mutableStateOf(false) }

    Box {
        Button(onClick = { expanded = true }) {
            Text(text = selectedCategory.uppercase())
        }

        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            categories.forEach { category ->
                DropdownMenuItem(
                    text = { Text(category.uppercase()) },
                    onClick = {
                        onCategorySelected(category)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun NewsItem(news: News, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                navController.navigate("detail/${news.title}/${news.content}")
            },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(text = news.title, style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(8.dp))
            Text(text = news.content, maxLines = 2, style = MaterialTheme.typography.bodyMedium)
        }
    }
}


