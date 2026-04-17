package com.palestine.roots.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.palestine.roots.domain.model.Site
import com.palestine.roots.viewmodel.HomeViewModel

import androidx.compose.ui.res.stringResource
import com.palestine.roots.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(
    onBack: () -> Unit,
    onSiteSelected: (Site) -> Unit,
    viewModel: HomeViewModel = viewModel()
) {
    val favoriteSites by viewModel.favoriteSites.collectAsState()
    val language by viewModel.language.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.nav_favorites), color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null, tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
            )
        }
    ) { padding ->
        if (favoriteSites.isEmpty()) {
            EmptyFavorites()
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.padding(padding).fillMaxSize().background(MaterialTheme.colorScheme.background)
            ) {
                items(favoriteSites) { site ->
                    SiteCard(
                        site = site,
                        onSiteSelected = onSiteSelected,
                        onToggleFavorite = { viewModel.toggleFavorite(it) },
                        language = language
                    )
                }
            }
        }
    }
}

@Composable
fun EmptyFavorites() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(Icons.Default.Favorite, null, modifier = Modifier.size(64.dp), tint = Color.LightGray)
        Text(stringResource(R.string.favorites_empty), color = Color.Gray, fontWeight = FontWeight.Medium)
    }
}
