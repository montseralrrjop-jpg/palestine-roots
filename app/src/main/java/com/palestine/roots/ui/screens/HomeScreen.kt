package com.palestine.roots.ui.screens

import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.palestine.roots.domain.model.Site
import com.palestine.roots.ui.states.SiteUiState
import com.palestine.roots.viewmodel.HomeViewModel

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import com.palestine.roots.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onSiteSelected: (Site) -> Unit,
    onNavigateToMap: () -> Unit,
    onNavigateToFavorites: () -> Unit,
    viewModel: HomeViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val isDarkMode by viewModel.isDarkMode.collectAsState()
    val language by viewModel.language.collectAsState()

    Scaffold(
        topBar = {
            Column(modifier = Modifier.background(MaterialTheme.colorScheme.primary)) {
                TopAppBar(
                    title = { Text(stringResource(R.string.app_name), color = Color.White, fontWeight = FontWeight.Bold) },
                    actions = {
                        IconButton(onClick = onNavigateToMap) {
                            Icon(Icons.Default.Map, stringResource(R.string.nav_map), tint = Color.White)
                        }
                        IconButton(onClick = onNavigateToFavorites) {
                            Icon(Icons.Default.Favorite, stringResource(R.string.nav_favorites), tint = Color.White)
                        }
                        IconButton(onClick = { 
                            val nextLang = if (language == "ar") "en" else "ar"
                            viewModel.setLanguage(nextLang)
                        }) {
                            Icon(
                                imageVector = Icons.Default.Language,
                                contentDescription = stringResource(R.string.settings_language),
                                tint = Color.White
                            )
                        }
                        IconButton(onClick = { viewModel.toggleDarkMode(!isDarkMode) }) {
                            Icon(
                                imageVector = if (isDarkMode) Icons.Default.LightMode else Icons.Default.DarkMode,
                                contentDescription = stringResource(R.string.settings_dark_mode),
                                tint = Color.White
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
                )
                // شريط البحث الذكي
                SearchBar(
                    query = searchQuery,
                    onQueryChange = { viewModel.onSearchQueryChanged(it) },
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(MaterialTheme.colorScheme.background)
        ) {
            // فلتر المحافظات
            ProvinceFilter(
                provinces = viewModel.provinces,
                onProvinceSelected = { viewModel.filterByCity(it) },
                language = language
            )

            // عرض النتائج بناءً على الحالة
            when (val state = uiState) {
                is SiteUiState.Loading -> {
                    SiteList(
                        sites = emptyList(),
                        onSiteSelected = {},
                        onToggleFavorite = {},
                        language = language,
                        isLoading = true
                    )
                }
                is SiteUiState.Empty -> EmptyState()
                is SiteUiState.Success -> {
                    SiteList(
                        sites = state.sites,
                        onSiteSelected = onSiteSelected,
                        onToggleFavorite = { viewModel.toggleFavorite(it) },
                        language = language,
                        isLoading = false
                    )
                }
                is SiteUiState.Error -> ErrorState(state.message)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .clip(RoundedCornerShape(28.dp)),
        placeholder = { Text(stringResource(R.string.search_hint)) },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            disabledContainerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        singleLine = true
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProvinceFilter(
    provinces: List<String>,
    onProvinceSelected: (String) -> Unit,
    language: String = "ar"
) {
    val translatedProvinces = if (language == "en") {
        listOf(
            "Jerusalem", "Ramallah", "Nablus", "Jenin", 
            "Tulkarm", "Qalqilya", "Salfit", "Jericho", 
            "Hebron", "Bethlehem"
        )
    } else {
        provinces
    }

    LazyRow(
        modifier = Modifier.padding(vertical = 12.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(translatedProvinces.size) { index ->
            FilterChip(
                selected = false,
                onClick = { onProvinceSelected(provinces[index]) },
                label = { Text(translatedProvinces[index]) }
            )
        }
    }
}

@Composable
fun SiteList(
    sites: List<Site>,
    onSiteSelected: (Site) -> Unit,
    onToggleFavorite: (Site) -> Unit,
    language: String = "ar",
    isLoading: Boolean = false
) {
    if (isLoading) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(6) {
                SkeletonSiteCard()
            }
        }
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(sites) { site ->
                SiteCard(site, onSiteSelected, onToggleFavorite, language)
            }
        }
    }
}

@Composable
fun SkeletonSiteCard() {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(Color.LightGray.copy(alpha = 0.3f))
            )
            Column(modifier = Modifier.padding(12.dp)) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(14.dp)
                        .background(Color.LightGray.copy(alpha = 0.3f))
                )
                Spacer(modifier = Modifier.height(8.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .height(12.dp)
                        .background(Color.LightGray.copy(alpha = 0.3f))
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SiteCard(
    site: Site,
    onSiteSelected: (Site) -> Unit,
    onToggleFavorite: (Site) -> Unit,
    language: String = "ar"
) {
    val haptic = LocalHapticFeedback.current
    val name = if (language == "en") site.nameEn else site.name
    val city = if (language == "en") site.cityEn else site.city
    val category = if (language == "en") site.categoryEn else site.category

    Card(
        onClick = { onSiteSelected(site) },
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp), // تحسين حجم اللمس
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column {
            Box {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(site.imageUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = name, // إمكانية الوصول
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),
                    contentScale = ContentScale.Crop
                )
                IconButton(
                    onClick = { 
                        haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                        onToggleFavorite(site) 
                    },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .size(48.dp) // حجم لمس معياري
                ) {
                    Icon(
                        imageVector = if (site.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = if (site.isFavorite) "Remove from favorites" else "Add to favorites",
                        tint = if (site.isFavorite) Color.Red else Color.White
                    )
                }
            }
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = name, 
                    fontWeight = FontWeight.Bold, 
                    fontSize = 14.sp, 
                    maxLines = 1,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = city, 
                    fontSize = 12.sp, 
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = category,
                    fontSize = 10.sp, 
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
fun LoadingState() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
fun EmptyState() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(Icons.Default.SearchOff, null, modifier = Modifier.size(64.dp), tint = Color.Gray)
        Text(stringResource(R.string.no_results), color = Color.Gray)
    }
}

@Composable
fun ErrorState(message: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("خطأ: $message", color = Color.Red)
    }
}
