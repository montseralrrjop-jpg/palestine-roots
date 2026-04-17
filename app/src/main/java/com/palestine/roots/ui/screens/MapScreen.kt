package com.palestine.roots.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import com.google.maps.android.compose.clustering.Clustering
import com.palestine.roots.domain.model.Site
import com.palestine.roots.ui.states.SiteUiState
import com.palestine.roots.viewmodel.HomeViewModel
import com.google.maps.android.clustering.ClusterItem
import kotlinx.coroutines.launch

import androidx.compose.ui.res.stringResource
import com.palestine.roots.R

// صنف لتمثيل عنصر على الخريطة يدعم التجميع (Clustering)
data class SiteClusterItem(
    val site: Site,
    private val itemTitle: String,
    private val itemSnippet: String
) : ClusterItem {
    override fun getPosition(): LatLng = LatLng(site.latitude, site.longitude)
    override fun getTitle(): String = itemTitle
    override fun getSnippet(): String = itemSnippet
    override fun getZIndex(): Float? = null
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(
    onBack: () -> Unit,
    onSiteSelected: (Site) -> Unit,
    viewModel: HomeViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    val language by viewModel.language.collectAsState()
    val scope = rememberCoroutineScope()
    
    // موقع الكاميرا الافتراضي (فلسطين - القدس)
    val jerusalem = LatLng(31.7719, 35.2170)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(jerusalem, 9f)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.nav_map), color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null, tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    scope.launch {
                        cameraPositionState.animate(
                            update = CameraUpdateFactory.newLatLngZoom(jerusalem, 10f),
                            durationMs = 1000
                        )
                    }
                },
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimaryContainer
            ) {
                Icon(Icons.Default.MyLocation, contentDescription = "My Location")
            }
        }
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize().padding(padding)) {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
                properties = MapProperties(isMyLocationEnabled = false),
                uiSettings = MapUiSettings(zoomControlsEnabled = true)
            ) {
                if (uiState is SiteUiState.Success) {
                    val clusterItems = remember((uiState as SiteUiState.Success).sites, language) {
                        (uiState as SiteUiState.Success).sites.map { 
                            val title = if (language == "en") it.nameEn else it.name
                            val snippet = if (language == "en") it.cityEn else it.city
                            SiteClusterItem(it, title, snippet)
                        }
                    }
                    
                    Clustering(
                        items = clusterItems,
                        onClusterItemInfoWindowClick = { item ->
                            onSiteSelected(item.site)
                            true
                        }
                    )
                }
            }
        }
    }
}
