package com.palestine.roots.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.palestine.roots.data.local.PreferencesManager
import com.palestine.roots.domain.model.Site
import com.palestine.roots.domain.repository.SiteRepository
import com.palestine.roots.ui.states.SiteUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * الـ ViewModel المطور للتحكم في تدفق البيانات بين قاعدة البيانات والواجهة.
 * يطبق معايير Clean Architecture عبر استخدام Repository وحقن التبعية Hilt.
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: SiteRepository,
    private val preferencesManager: PreferencesManager
) : ViewModel() {

    // حالة الواجهة (البحث، التحميل، النتائج)
    private val _uiState = MutableStateFlow<SiteUiState>(SiteUiState.Loading)
    val uiState: StateFlow<SiteUiState> = _uiState.asStateFlow()

    // حالة البحث (نص البحث)
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    // المحافظات الشاملة (الضفة الغربية)
    val provinces = listOf(
        "القدس", "رام الله والبيرة", "نابلس", "جنين", 
        "طولكرم", "قلقيلية", "سلفيت", "أريحا والأغوار", 
        "الخليل", "بيت لحم"
    )

    // مراقبة الوضع الليلي من DataStore
    val isDarkMode: StateFlow<Boolean> = preferencesManager.isDarkMode
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), false)

    // مراقبة اللغة المفضلة
    val language: StateFlow<String> = preferencesManager.language
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), "ar")

    // مراقبة حالة إتمام شاشة الترحيب
    val isOnboardingCompleted: StateFlow<Boolean> = preferencesManager.isOnboardingCompleted
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), true)

    // مراقبة المواقع المفضلة مباشرة من قاعدة البيانات
    val favoriteSites: StateFlow<List<Site>> = repository.getFavoriteSites()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    init {
        // تحميل كافة المواقع عند البداية
        loadAllSites()
    }

    /**
     * تحميل كافة المواقع ومراقبة التغييرات
     */
    fun loadAllSites() {
        viewModelScope.launch {
            repository.getAllSites().collect { sites ->
                _uiState.value = if (sites.isEmpty()) SiteUiState.Empty else SiteUiState.Success(sites)
            }
        }
    }

    /**
     * البحث المتقدم (يعمل لحظياً مع كل حرف يكتبه المستخدم)
     */
    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
        if (query.isBlank()) {
            loadAllSites()
        } else {
            viewModelScope.launch {
                repository.searchSites(query).collect { sites ->
                    _uiState.value = if (sites.isEmpty()) SiteUiState.Empty else SiteUiState.Success(sites)
                }
            }
        }
    }

    /**
     * الفلترة حسب المدينة
     */
    fun filterByCity(city: String) {
        viewModelScope.launch {
            repository.getSitesByCity(city).collect { sites ->
                _uiState.value = if (sites.isEmpty()) SiteUiState.Empty else SiteUiState.Success(sites)
            }
        }
    }

    /**
     * إضافة/حذف من المفضلة (تحديث مباشر في قاعدة البيانات)
     */
    fun toggleFavorite(site: Site) {
        viewModelScope.launch {
            repository.toggleFavorite(site.id, !site.isFavorite)
        }
    }

    /**
     * تغيير الثيم وحفظه في DataStore
     */
    fun toggleDarkMode(enabled: Boolean) {
        viewModelScope.launch {
            preferencesManager.toggleDarkMode(enabled)
        }
    }

    /**
     * تغيير اللغة وحفظها في DataStore
     */
    fun setLanguage(language: String) {
        viewModelScope.launch {
            preferencesManager.setLanguage(language)
        }
    }

    /**
     * حفظ حالة إتمام شاشة الترحيب
     */
    fun completeOnboarding() {
        viewModelScope.launch {
            preferencesManager.setOnboardingCompleted(true)
        }
    }
}
