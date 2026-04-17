package com.palestine.roots

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.palestine.roots.domain.model.Site
import com.palestine.roots.ui.screens.*
import com.palestine.roots.ui.theme.PalestineRootsTheme
import com.palestine.roots.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val homeViewModel: HomeViewModel = hiltViewModel()
            val isDarkMode by homeViewModel.isDarkMode.collectAsState()
            val isOnboardingCompleted by homeViewModel.isOnboardingCompleted.collectAsState()
            val language by homeViewModel.language.collectAsState()

            // تحديث اللغة (Locale) بشكل ديناميكي
            LaunchedEffect(language) {
                updateLocale(language)
            }

            PalestineRootsTheme(darkTheme = isDarkMode) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PalestineRootsApp(homeViewModel, isOnboardingCompleted)
                }
            }
        }
    }

    private fun updateLocale(language: String) {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val config = resources.configuration
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
        
        // لإعادة بناء النشاط وتطبيق تغيير اللغة فوراً (اختياري، يفضل استخدام CompositionLocal للغة)
        // recreate()
    }
}

@Composable
fun PalestineRootsApp(homeViewModel: HomeViewModel, isOnboardingCompleted: Boolean) {
    val navController = rememberNavController()
    
    // متغير لحفظ الموقع المختار مؤقتاً عند التنقل للتفاصيل
    var selectedSite by remember { mutableStateOf<Site?>(null) }

    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        composable("splash") {
            SplashScreen(
                onNavigateToLogin = { 
                    if (isOnboardingCompleted) {
                        navController.navigate("login") { popUpTo("splash") { inclusive = true } }
                    } else {
                        navController.navigate("onboarding") { popUpTo("splash") { inclusive = true } }
                    }
                },
                onNavigateToHome = { navController.navigate("home") { popUpTo("splash") { inclusive = true } } }
            )
        }

        composable("onboarding") {
            OnboardingScreen(
                onFinished = {
                    homeViewModel.completeOnboarding()
                    navController.navigate("login") { popUpTo("onboarding") { inclusive = true } }
                }
            )
        }

        composable("login") {
            LoginScreen(
                onLoginSuccess = { navController.navigate("home") { popUpTo("login") { inclusive = true } } },
                onLoginError = { /* Handle error */ }
            )
        }

        composable(
            "home",
            enterTransition = { slideInHorizontally(initialOffsetX = { 1000 }) + fadeIn() },
            exitTransition = { slideOutHorizontally(targetOffsetX = { -1000 }) + fadeOut() }
        ) {
            HomeScreen(
                onSiteSelected = { site ->
                    selectedSite = site
                    navController.navigate("site_detail")
                },
                onNavigateToMap = { navController.navigate("map") },
                onNavigateToFavorites = { navController.navigate("favorites") },
                viewModel = homeViewModel
            )
        }

        composable(
            "map",
            enterTransition = { fadeIn(animationSpec = tween(500)) },
            exitTransition = { fadeOut(animationSpec = tween(500)) }
        ) {
            MapScreen(
                onBack = { navController.popBackStack() },
                onSiteSelected = { site ->
                    selectedSite = site
                    navController.navigate("site_detail")
                },
                viewModel = homeViewModel
            )
        }

        composable("favorites") {
            FavoritesScreen(
                onBack = { navController.popBackStack() },
                onSiteSelected = { site ->
                    selectedSite = site
                    navController.navigate("site_detail")
                },
                viewModel = homeViewModel
            )
        }

        composable("site_detail") {
            selectedSite?.let { site ->
                SiteDetailScreen(
                    site = site,
                    onBack = { navController.popBackStack() },
                    viewModel = homeViewModel
                )
            }
        }
    }
}
