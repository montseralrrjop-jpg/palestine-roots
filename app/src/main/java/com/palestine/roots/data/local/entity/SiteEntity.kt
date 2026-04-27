package com.palestine.roots.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * تمثيل جدول المواقع في قاعدة البيانات (Room Entity).
 * يحتوي على كافة التفاصيل اللازمة لكل موقع أثري أو تاريخي.
 */
@Entity(tableName = "sites")
data class SiteEntity(
    @PrimaryKey val id: String,
    val name: String,
    val nameEn: String,
    val city: String,
    val cityEn: String,
    val description: String,
    val descriptionEn: String,
    val history: String,
    val historyEn: String,
    val imageUrl: String,
    val latitude: Double,
    val longitude: Double,
    val category: String,
    val categoryEn: String,
    val foundationYear: String,
    val isFavorite: Boolean = false
)
