package com.palestine.roots.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.palestine.roots.data.local.entity.SiteEntity
import kotlinx.coroutines.flow.Flow

/**
 * واجهة التعامل مع قاعدة البيانات (Data Access Object).
 * تحتوي على العمليات الأساسية لجلب والبحث وفلترة المواقع.
 */
@Dao
interface SiteDao {

    @Query("SELECT * FROM sites")
    fun getAllSites(): Flow<List<SiteEntity>>

    @Query("SELECT * FROM sites WHERE city LIKE '%' || :cityName || '%' OR cityEn LIKE '%' || :cityName || '%'")
    fun getSitesByCity(cityName: String): Flow<List<SiteEntity>>

    @Query("SELECT * FROM sites WHERE id = :siteId")
    suspend fun getSiteById(siteId: String): SiteEntity?

    @Query("SELECT * FROM sites WHERE name LIKE '%' || :query || '%' OR description LIKE '%' || :query || '%' OR nameEn LIKE '%' || :query || '%' OR descriptionEn LIKE '%' || :query || '%' OR city LIKE '%' || :query || '%' OR cityEn LIKE '%' || :query || '%'")
    fun searchSites(query: String): Flow<List<SiteEntity>>

    @Query("SELECT * FROM sites WHERE category = :category OR categoryEn = :category")
    fun getSitesByCategory(category: String): Flow<List<SiteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSites(sites: List<SiteEntity>)

    @Query("UPDATE sites SET isFavorite = :isFavorite WHERE id = :siteId")
    suspend fun updateFavoriteStatus(siteId: String, isFavorite: Boolean)

    @Query("SELECT * FROM sites WHERE isFavorite = 1")
    fun getFavoriteSites(): Flow<List<SiteEntity>>
}
