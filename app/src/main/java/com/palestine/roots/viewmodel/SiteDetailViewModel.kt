package com.palestine.roots.viewmodel

import androidx.lifecycle.ViewModel
import com.palestine.roots.data.local.LocalDataSource
import com.palestine.roots.data.model.Site
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SiteDetailViewModel : ViewModel() {
    
    private val _site = MutableStateFlow<Site?>(null)
    val site: StateFlow<Site?> = _site
    
    // تحميل موقع محلياً
    fun loadSite(siteId: String) {
        _site.value = LocalDataSource.getSiteById(siteId)
    }
    
    // دالة مساعدة للحصول على رابط خرائط جوجل للتنقل
    fun getGoogleMapsUrl(latitude: Double, longitude: Double, siteName: String): String {
        return "https://www.google.com/maps/dir/?api=1&destination=$latitude,$longitude&travelmode=driving&destination_place_id=$siteName"
    }
}
