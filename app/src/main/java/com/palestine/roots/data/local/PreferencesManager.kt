package com.palestine.roots.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// تعريف ملف تخزين البيانات
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class PreferencesManager(private val context: Context) {

    companion object {
        // مفتاح تخزين معرفات المواقع المفضلة (مخزنة كمجموعة نصوص)
        val FAVORITE_SITES_KEY = stringSetPreferencesKey("favorite_sites")
        // مفتاح تخزين تفضيل الوضع الليلي
        val DARK_MODE_KEY = booleanPreferencesKey("dark_mode")
        // مفتاح تخزين حالة إتمام شاشة الترحيب
        val ONBOARDING_COMPLETED_KEY = booleanPreferencesKey("onboarding_completed")
        // مفتاح تخزين اللغة المفضلة (ar, en)
        val LANGUAGE_KEY = stringPreferencesKey("language")
    }

    // جلب اللغة المفضلة كـ Flow
    val language: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[LANGUAGE_KEY] ?: "ar"
        }

    // جلب قائمة المفضلات كـ Flow
    val favoriteSites: Flow<Set<String>> = context.dataStore.data
        .map { preferences ->
            preferences[FAVORITE_SITES_KEY] ?: emptySet()
        }

    // جلب تفضيل الوضع الليلي كـ Flow
    val isDarkMode: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[DARK_MODE_KEY] ?: false
        }

    // جلب حالة إتمام شاشة الترحيب
    val isOnboardingCompleted: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[ONBOARDING_COMPLETED_KEY] ?: false
        }

    // حفظ اللغة المفضلة
    suspend fun setLanguage(language: String) {
        context.dataStore.edit { preferences ->
            preferences[LANGUAGE_KEY] = language
        }
    }

    // تبديل حالة الوضع الليلي
    suspend fun toggleDarkMode(enabled: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[DARK_MODE_KEY] = enabled
        }
    }

    // حفظ حالة إتمام شاشة الترحيب
    suspend fun setOnboardingCompleted(completed: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[ONBOARDING_COMPLETED_KEY] = completed
        }
    }

    // إضافة أو حذف موقع من المفضلة
    suspend fun toggleFavorite(siteId: String) {
        context.dataStore.edit { preferences ->
            val currentFavorites = preferences[FAVORITE_SITES_KEY] ?: emptySet()
            val newFavorites = currentFavorites.toMutableSet()
            
            if (newFavorites.contains(siteId)) {
                newFavorites.remove(siteId) // حذف إذا كان موجوداً
            } else {
                newFavorites.add(siteId) // إضافة إذا لم يكن موجوداً
            }
            
            preferences[FAVORITE_SITES_KEY] = newFavorites
        }
    }
}
