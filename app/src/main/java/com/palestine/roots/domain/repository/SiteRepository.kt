package com.palestine.roots.domain.repository

import com.palestine.roots.domain.model.Site
import kotlinx.coroutines.flow.Flow

/**
 * واجهة مستودع البيانات (Repository Interface).
 * تحدد العمليات التي يمكن للـ ViewModel طلبها دون معرفة مصدر البيانات (Room, API, etc).
 */
interface SiteRepository {
    fun getAllSites(): Flow<List<Site>>
    fun getSitesByCity(cityName: String): Flow<List<Site>>
    suspend fun getSiteById(siteId: String): Site?
    fun searchSites(query: String): Flow<List<Site>>
    fun getFavoriteSites(): Flow<List<Site>>
    suspend fun toggleFavorite(siteId: String, isFavorite: Boolean)
}
